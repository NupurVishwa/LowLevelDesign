public class Payment {
    private final int amount;
    private final PaymentType paymentType;

    public Payment(int amount, PaymentType paymentType) {
        if(amount <0){
            throw new IllegalArgumentException("Payment amount must be greater than 0");
        }
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getAmount() {
        return amount;
    }
}
