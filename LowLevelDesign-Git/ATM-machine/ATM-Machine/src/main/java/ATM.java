class ATM {

    private final BankingService bankingService;
    private final CashDispenser cashDispenser;

    private ATMState currentState;

    private Card currentCard;

    public ATM(BankingService bankingService, CashDispenser cashDispenser) {

        this.bankingService = bankingService;
        this.cashDispenser = cashDispenser;
        this.currentState = new IdleState(this);
    }

    public void setState(ATMState state) {
        this.currentState = state;
    }

    public ATMState getState() {
        return currentState;
    }

    public BankingService getBankingService() {
        return bankingService;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public void insertCard(Card card) {
        currentState.insertCard(card);
    }

    public void authenticate(String pin) {
        currentState.authenticate(pin);
    }

    public void checkBalance() {
        currentState.checkBalance();
    }

    public void withdraw(double amount) {
        currentState.withdraw(amount);
    }

    public void deposit(double amount) {
        currentState.deposit(amount);
    }

    public void ejectCard() {
        currentState.ejectCard();
    }
}
