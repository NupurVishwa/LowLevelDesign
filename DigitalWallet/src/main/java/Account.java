import java.math.BigDecimal;

public class Account {

    private String id;
    private User user;
    private BigDecimal balance;
    private Currency currency;

    public Account(
            String id,
            User user,
            Currency currency
    ) {
        this.id = id;
        this.user = user;
        this.currency = currency;
        this.balance = BigDecimal.ZERO;

        user.addAccount(this);
    }

    public void deposit(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero"
            );
        }

        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero"
            );
        }

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException(
                    "Insufficient funds"
            );
        }

        balance = balance.subtract(amount);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }
}