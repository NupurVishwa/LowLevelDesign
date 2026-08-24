class AuthenticatedState implements ATMState {

    private final ATM atm;

    public AuthenticatedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void checkBalance() {

        String accountNumber = atm.getCurrentCard().getAccountnumber();

        double balance = atm.getBankingService().getBalance(accountNumber);

        System.out.println("Current balance: ₹" + balance);
    }

    @Override
    public synchronized void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }

        if (amount % 100 != 0) {
            throw new IllegalArgumentException("Amount must be multiple of ₹100");
        }

        String accountNumber = atm.getCurrentCard().getAccountnumber();

        double balance = atm.getBankingService().getBalance(accountNumber);

        if (balance < amount) {
            throw new IllegalStateException("Insufficient account balance");
        }

        CashDispenser dispenser = atm.getCashDispenser();

        /*
         * Important:
         *
         * We check ATM cash before debiting
         * the customer's account.
         */
        if (!dispenser.canDispense((int) amount)) {
            throw new IllegalStateException("ATM does not have required cash");
        }

        /*
         * In a real distributed banking system,
         * debit + cash dispensing requires a
         * transactional/reservation mechanism.
         *
         * For this in-memory interview implementation
         * we synchronize the operation.
         */
        synchronized (atm) {

            // Re-check balance inside critical section
            balance = atm.getBankingService().getBalance(accountNumber);

            if (balance < amount) {
                throw new IllegalStateException("Insufficient balance");
            }

            Transaction transaction = TransactionFactory.create(TransactionType.WITHDRAWAL, accountNumber, amount);

            atm.getBankingService().processTransaction(transaction);

            try {

                dispenser.dispenseCash((int) amount);

            } catch (RuntimeException e) {

                /*
                 * Production system should use
                 * transaction compensation / reversal.
                 */
                throw new IllegalStateException("Cash dispensing failed", e);
            }
        }
    }

    @Override
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid deposit amount"
            );
        }

        String accountNumber = atm.getCurrentCard().getAccountnumber();

        Transaction transaction = TransactionFactory.create(TransactionType.DEPOSIT, accountNumber, amount);

        atm.getBankingService().processTransaction(transaction);

        System.out.println("Cash deposited successfully.");
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalStateException("Card already inserted");
    }

    @Override
    public void authenticate(String pin) {
        throw new IllegalStateException("Already authenticated");
    }

    @Override
    public void ejectCard() {

        atm.setCurrentCard(null);

        atm.setState(new IdleState(atm));

        System.out.println("Transaction completed. Card ejected.");
    }
}