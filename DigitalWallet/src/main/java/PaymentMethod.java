public abstract class PaymentMethod {

    protected String id;
    protected User user;
    protected PaymentMethodType type;

    public PaymentMethod(
            String id,
            User user,
            PaymentMethodType type
    ) {
        this.id = id;
        this.user = user;
        this.type = type;
    }

    public abstract boolean validate();

    public abstract void processPayment(double amount);

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public PaymentMethodType getType() {
        return type;
    }
}