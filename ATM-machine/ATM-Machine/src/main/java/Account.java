public class Account {
    private final String accountnumber;
    private double balance;

    public Account(String accountnumber , double balance) {
        this.accountnumber = accountnumber;
        this.balance= balance;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public synchronized double getBalance() {
        return balance;
    }
    public synchronized void debit(double amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (balance < amount) {
            throw new IllegalStateException("Insufficient account balance");
        }

        balance -= amount;
    }
    public synchronized void credit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        balance += amount;
    }

}
