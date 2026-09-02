import java.math.BigDecimal;

public class DigitalWalletDemo {

    public static void main(String[] args) {

        // Create users
        User user1 = new User(
                "John Doe",
                "john@example.com",
                "1234567890"
        );

        User user2 = new User(
                "Jane Doe",
                "jane@example.com",
                "9876543210"
        );

        // Create wallets
        DigitalWallet wallet1 = user1.getWallet();
        DigitalWallet wallet2 = user2.getWallet();

        // Add bank account
        BankAccount bankAccount = new BankAccount(
                "PM001",
                user1,
                "1234567890",
                "HDFC Bank",
                "HDFC0001234"
        );

        wallet1.addPaymentMethod(bankAccount);

        // Add money
        wallet1.addMoney(
                new BigDecimal("1000.00"),
                Currency.USD
        );

        System.out.println(
                "Wallet 1 Balance: " +
                        wallet1.getBalance()
        );

        // Send money
        wallet1.sendMoney(
                wallet2,
                new BigDecimal("500.00"),
                Currency.USD
        );

        System.out.println(
                "Wallet 1 Balance: " +
                        wallet1.getBalance()
        );

        System.out.println(
                "Wallet 2 Balance: " +
                        wallet2.getBalance()
        );
    }
}