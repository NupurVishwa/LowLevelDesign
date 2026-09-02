public class BankAccount extends PaymentMethod {

    private String accountNumber;
    private String bankName;
    private String ifscCode;

    public BankAccount(
            String id,
            User user,
            String accountNumber,
            String bankName,
            String ifscCode
    ) {

        super(
                id,
                user,
                PaymentMethodType.BANK_ACCOUNT
        );

        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
    }

    @Override
    public boolean validate() {

        return accountNumber != null
                && !accountNumber.isEmpty()
                && bankName != null
                && !bankName.isEmpty()
                && ifscCode != null
                && !ifscCode.isEmpty();
    }

    public void transfer(double amount) {

        if (!validate()) {
            throw new IllegalArgumentException(
                    "Invalid bank account"
            );
        }

        System.out.println(
                "Transferred " + amount +
                        " using bank account"
        );
    }

    @Override
    public void processPayment(double amount) {
        transfer(amount);
    }
}