import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Wallet {

    private String id;
    private String userId;
    private double balance;

    // Stores all wallets using userId as the key
    private static Map<String, Wallet> wallets = new HashMap<>();

    public Wallet(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.balance = 0.0;

        // Register wallet
        wallets.put(userId, this);
    }

    // Return wallet associated with the user
    public static Wallet getWalletByUserId(String userId) {
        return wallets.get(userId);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        balance -= amount;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }
}