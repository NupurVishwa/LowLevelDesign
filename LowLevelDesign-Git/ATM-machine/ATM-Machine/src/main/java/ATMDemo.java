public class ATMDemo {

    public static void main(String[] args) {

        // Create bank
        BankingService bankService = new BankingService();
        String accountNumber = "1234";
        // Create account
        bankService.createAccount("1234", 1000);

        // Create card
        Card card = new Card("CARD123", "1234", "1234567890");

        // Register card
        bankService.registerCard(card);

        // Create ATM
        CashDispenser cashDispenser = new CashDispenser();

        ATM atm = new ATM(bankService, cashDispenser);

        // Insert card
        atm.insertCard(card);

        // Authenticate
        atm.authenticate("1234567890");

        // Check balance
        atm.checkBalance();

        // Withdraw
        atm.withdraw(500);

        // Check balance again
        atm.checkBalance();

        // Deposit
        atm.deposit(200);

        // Check balance
        atm.checkBalance();

        // Eject card
        atm.ejectCard();
    }
}
