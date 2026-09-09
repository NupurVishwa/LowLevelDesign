package Service;
import Factory.SplitStrategyFactory;
import Model.*;
import Observer.BalanceObserver;
import Enum.SplitType;
import Strategy.SplitStrategy;
import Exception.UserNotFoundException;
import Exception.InvalidSplitException;
import Exception.InvalidAmountException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SplitwiseService {

    private static volatile SplitwiseService instance;

    private final Map<String, User> users;
    private final Map<String, Group> groups;
    private final Map<String, Expense> expenses;
    private final Map<String, Transaction> transactions;

    private final List<BalanceObserver> observers;

    private SplitwiseService() {

        users = new HashMap<>();
        groups = new HashMap<>();
        expenses = new HashMap<>();
        transactions = new HashMap<>();
        observers = new ArrayList<>();
    }

    /*
     * Singleton Pattern
     */
    public static SplitwiseService getInstance() {

        if (instance == null) {

            synchronized (SplitwiseService.class) {

                if (instance == null) {
                    instance = new SplitwiseService();
                }
            }
        }

        return instance;
    }

    // ----------------------------------------------------
    // OBSERVER
    // ----------------------------------------------------

    public void addObserver(BalanceObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Expense expense) {

        for (BalanceObserver observer : observers) {
            observer.onBalanceUpdated(expense);
        }
    }

    // ----------------------------------------------------
    // USER MANAGEMENT
    // ----------------------------------------------------

    public User addUser(
            String name,
            String email) {

        String id = UUID.randomUUID().toString();

        User user = new User(
                id,
                name,
                email
        );

        users.put(id, user);

        return user;
    }

    public User getUser(String userId) {

        User user = users.get(userId);

        if (user == null) {

            throw new UserNotFoundException(
                    "User not found: " + userId
            );
        }

        return user;
    }

    // ----------------------------------------------------
    // GROUP MANAGEMENT
    // ----------------------------------------------------

    public Group createGroup(String name) {

        String id = UUID.randomUUID().toString();

        Group group = new Group(
                id,
                name
        );

        groups.put(id, group);

        return group;
    }

    // ----------------------------------------------------
    // EXPENSE MANAGEMENT
    // ----------------------------------------------------

    public Expense addExpense(
            String description,
            BigDecimal amount,
            User paidBy,
            List<User> users,
            SplitType splitType,
            List<BigDecimal> splitValues) {

        validateAmount(amount);

        if (paidBy == null) {
            throw new UserNotFoundException(
                    "Payer cannot be null"
            );
        }

        if (users == null || users.isEmpty()) {

            throw new InvalidSplitException(
                    "Expense must have participants"
            );
        }

        SplitStrategy strategy =
                SplitStrategyFactory.getStrategy(splitType);

        List<Split> splits =
                strategy.calculateSplits(
                        amount,
                        users,
                        splitValues
                );

        Expense expense = new Expense(
                UUID.randomUUID().toString(),
                description,
                amount,
                paidBy,
                users,
                splitType
        );

        expense.setSplits(splits);

        expenses.put(
                expense.getId(),
                expense
        );

        /*
         * Update balances.
         */
        updateBalances(expense);

        notifyObservers(expense);

        return expense;
    }

    private void validateAmount(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidAmountException(
                    "Expense amount must be greater than zero"
            );
        }
    }

    // ----------------------------------------------------
    // BALANCE MANAGEMENT
    // ----------------------------------------------------

    private void updateBalances(Expense expense) {

        User payer = expense.getPaidBy();

        for (Split split : expense.getSplits()) {

            User participant = split.getUser();

            /*
             * Payer's own share does not create debt.
             */
            if (participant.equals(payer)) {
                continue;
            }

            BigDecimal amount = split.getAmount();

            /*
             * participant owes payer.
             */
            participant.addBalance(
                    payer,
                    amount
            );

            /*
             * From payer's perspective,
             * participant owes payer.
             */
            payer.addBalance(
                    participant,
                    amount.negate()
            );
        }
    }

    // ----------------------------------------------------
    // GET BALANCE
    // ----------------------------------------------------

    public BigDecimal getBalance(
            User user1,
            User user2) {

        return user1.getBalance(user2);
    }

    public void printBalances(User user) {

        System.out.println(
                "\n===== BALANCES FOR "
                        + user.getName()
                        + " ====="
        );

        Map<User, BigDecimal> balances =
                user.getBalances();

        if (balances.isEmpty()) {

            System.out.println(
                    "No outstanding balances."
            );

            return;
        }

        for (Map.Entry<User, BigDecimal> entry :
                balances.entrySet()) {

            BigDecimal balance = entry.getValue();

            if (balance.compareTo(BigDecimal.ZERO) > 0) {

                System.out.println(
                        entry.getKey().getName()
                                + " owes "
                                + user.getName()
                                + " ₹"
                                + balance
                );

            } else if (
                    balance.compareTo(BigDecimal.ZERO) < 0) {

                System.out.println(
                        user.getName()
                                + " owes "
                                + entry.getKey().getName()
                                + " ₹"
                                + balance.abs()
                );
            }
        }
    }

    // ----------------------------------------------------
    // SETTLEMENT
    // ----------------------------------------------------

    public Transaction settleExpense(
            User from,
            User to,
            BigDecimal amount) {

        validateAmount(amount);

        BigDecimal currentBalance =
                from.getBalance(to);

        /*
         * Negative means 'from' owes 'to'.
         */
        if (currentBalance.compareTo(
                amount.negate()) > 0) {

            throw new InvalidAmountException(
                    from.getName()
                            + " does not owe "
                            + to.getName()
                            + " enough money"
            );
        }

        /*
         * Remove debt.
         */
        from.addBalance(
                to,
                amount
        );

        to.addBalance(
                from,
                amount.negate()
        );

        Transaction transaction =
                new Transaction(
                        UUID.randomUUID().toString(),
                        from,
                        to,
                        amount
                );

        transaction.execute();

        transactions.put(
                transaction.getId(),
                transaction
        );

        return transaction;
    }
}