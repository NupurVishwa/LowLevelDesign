public class CreditCard extends PaymentMethod {

    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCard(
            String id,
            User user,
            String cardNumber,
            String expiryDate,
            String cvv
    ) {

        super(
                id,
                user,
                PaymentMethodType.CREDIT_CARD
        );

        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {

        return cardNumber != null
                && cardNumber.length() >= 12
                && expiryDate != null
                && cvv != null;
    }

    public void charge(double amount) {

        if (!validate()) {
            throw new IllegalArgumentException(
                    "Invalid credit card"
            );
        }

        System.out.println(
                "Charged " + amount +
                        " using credit card"
        );
    }

    @Override
    public void processPayment(double amount) {
        charge(amount);
    }
}