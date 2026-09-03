package Payment;

public class Payment {

    private final String paymentId;
    private final String paymentMethod;
    private final double amount;

    private PaymentStatus status;


    // Constructor
    public Payment(String paymentId, String paymentMethod, double amount) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }


    // Process payment
    public void processPayment() {
        // Payment processing logic

        status = PaymentStatus.COMPLETED;
    }


    // Getters
    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}