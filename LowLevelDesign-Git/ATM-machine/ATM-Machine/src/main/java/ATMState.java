public interface ATMState {

    void insertCard(Card card);

    void authenticate(String pin);

    void checkBalance();

    void withdraw(double amount);

    void deposit(double amount);

    void ejectCard();
}
