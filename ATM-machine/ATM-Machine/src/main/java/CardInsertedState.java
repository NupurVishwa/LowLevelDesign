class CardInsertedState implements ATMState {

    private final ATM atm;

    public CardInsertedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void authenticate(String pin) {

        Card card = atm.getCurrentCard();

        boolean authenticated = atm.getBankingService().authenticate(card, pin);

        if (!authenticated) {

            System.out.println("Invalid PIN");

            return;
        }

        atm.setState(new AuthenticatedState(atm));

        System.out.println(
                "Authentication successful."
        );
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalStateException("Card already inserted");
    }

    @Override
    public void checkBalance() {
        throw new IllegalStateException("Please authenticate first");
    }

    @Override
    public void withdraw(double amount) {
        throw new IllegalStateException("Please authenticate first");
    }

    @Override
    public void deposit(double amount) {
        throw new IllegalStateException("Please authenticate first");
    }

    @Override
    public void ejectCard() {

        atm.setCurrentCard(null);
        atm.setState(new IdleState(atm));

        System.out.println("Card ejected.");
    }
}
