package Model;

import Enums.PaymentStatus;

public class Payment {

    private int id;
    private int orderId;
    private double totalAmount;
    private PaymentStatus status;

    public Payment(int id, int orderId, double totalAmount) {
        this.id = id;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.status = PaymentStatus.PENDING;
    }

    // Get Payment ID
    public int getId() {
        return id;
    }

    // Get Order ID
    public int getOrderId() {
        return orderId;
    }

    // Get Payment Amount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Get Payment Status
    public PaymentStatus getStatus() {
        return status;
    }

    // Mark payment as completed
    public void markPaymentCompleted() {
        this.status = PaymentStatus.COMPLETED;
    }

    // Mark payment as paid
    public void markPaid() {
        this.status = PaymentStatus.COMPLETED;
    }

    // Mark payment as failed
    public void markPaymentFailed() {
        this.status = PaymentStatus.FAILED;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}