public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean PaymentProcessor(Payment payment) {
        System.out.println("Processing cash payment: $" + payment.getAmount());
        return true;
    }
}
