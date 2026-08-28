abstract class Transaction {

    protected final String accountNumber;
    protected final double amount;

    public Transaction(String accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public abstract void process(BankingService bankingService);

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
