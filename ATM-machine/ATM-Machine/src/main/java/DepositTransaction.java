class DepositTransaction extends Transaction {

    public DepositTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public void process(BankingService bankingService) {

        Account account = bankingService.getAccount(accountNumber);

        if (account == null) {
            throw new IllegalStateException("Account not found");
        }

        account.credit(amount);

        System.out.println(
                "Deposit successful: ₹" + amount
        );
    }
}