public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public void process(BankingService bankingService) {
        Account account = bankingService.getAccount(accountNumber);

        if (account == null) {
            throw new IllegalStateException("Account not found");
        }

        account.debit(amount);

        System.out.println("Withdrawal successful: ₹" + amount);
    }
}
