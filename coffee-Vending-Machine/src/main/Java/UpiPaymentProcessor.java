public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean PaymentProcessor(Payment payment) {
        System.out.println("Processing UPI payment: $" + payment.getAmount());
        return true;
    }
}
