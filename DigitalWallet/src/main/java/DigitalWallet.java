import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DigitalWallet {

    private String id;
    private User user;

    private BigDecimal balance;

    private List<PaymentMethod> paymentMethods;
    private List<Transaction> transactions;

    private static final Map<User, DigitalWallet> wallets =
            new HashMap<>();

    private DigitalWallet(User user) {

        this.id = UUID.randomUUID().toString();
        this.user = user;

        this.balance = BigDecimal.ZERO;

        this.paymentMethods = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public static DigitalWallet getWallet(User user) {

        if (!wallets.containsKey(user)) {

            wallets.put(
                    user,
                    new DigitalWallet(user)
            );
        }

        return wallets.get(user);
    }

    public static DigitalWallet getInstance() {
        throw new UnsupportedOperationException(
                "Use DigitalWallet.getWallet(user)"
        );
    }

    public void addMoney(
            BigDecimal amount,
            Currency currency
    ) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }

        balance = balance.add(amount);

        System.out.println(
                "Added " + amount +
                        " " + currency
        );
    }

    public void sendMoney(
            DigitalWallet recipient,
            BigDecimal amount,
            Currency currency
    ) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException(
                    "Insufficient wallet balance"
            );
        }

        balance = balance.subtract(amount);

        recipient.balance =
                recipient.balance.add(amount);

        System.out.println(
                "Sent " + amount +
                        " " + currency
        );
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addPaymentMethod(
            PaymentMethod paymentMethod
    ) {

        if (!paymentMethod.validate()) {
            throw new IllegalArgumentException(
                    "Invalid payment method"
            );
        }

        paymentMethods.add(paymentMethod);
    }

    public void removePaymentMethod(
            String paymentMethodId
    ) {

        paymentMethods.removeIf(
                pm -> pm.getId().equals(paymentMethodId)
        );
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}