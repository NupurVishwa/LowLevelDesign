public class NotificationFactory {

    public static Notification createNotification(
            int id,
            User recipient,
            String message,
            NotificationType type) {

        return new Notification(
                id,
                recipient,
                message,
                type
        );
    }
}