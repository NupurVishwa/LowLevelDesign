class IdleState implements ATMState {

    private final ATM atm;

    public IdleState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(Card card) {

        atm.setCurrentCard(card);

        atm.setState(new CardInsertedState(atm));

        System.out.println("Card inserted. Please enter PIN.");
    }

    @Override
    public void authenticate(String pin) {
        throw new IllegalStateException("Please insert card first");
    }

    @Override
    public void checkBalance() {
        throw new IllegalStateException("Please insert card first");
    }

    @Override
    public void withdraw(double amount) {
        throw new IllegalStateException("Please insert card first");
    }

    @Override
    public void deposit(double amount) {
        throw new IllegalStateException("Please insert card first");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card inserted.");
    }
}
