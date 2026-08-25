class TransactionFactory {

    public static Transaction create(TransactionType type, String accountNumber, double amount) {
        switch (type) {

            case WITHDRAWAL:
                return new WithdrawalTransaction(accountNumber, amount);

            case DEPOSIT:
                return new DepositTransaction(accountNumber, amount);

            default:
                throw new IllegalArgumentException("Unsupported transaction type");
        }
    }
}
