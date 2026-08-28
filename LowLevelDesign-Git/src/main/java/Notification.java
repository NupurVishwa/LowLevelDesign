class Notification {

    private int id;

    private User recipient;

    private String message;

    private NotificationType type;


    public Notification(
            int id,
            User recipient,
            String message,
            NotificationType type) {

        this.id = id;

        this.recipient = recipient;

        this.message = message;

        this.type = type;
    }


    public String getMessage() {

        return message;
    }


    public NotificationType getType() {

        return type;
    }
}
