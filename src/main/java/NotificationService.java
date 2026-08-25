import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    private Map<Integer, NotificationObserver>
            observers;

    private int notificationId;


    public NotificationService() {

        observers = new HashMap<>();

        notificationId = 1;
    }


    // User subscribes to notification system
    public void subscribe(
            User user,
            NotificationObserver observer) {

        observers.put(
                user.getId(),
                observer
        );
    }


    // Notify a particular user
    public void notifyUser(
            User user,
            NotificationType type,
            String message) {

        // Factory Pattern
        Notification notification =
                NotificationFactory.createNotification(
                        notificationId++,
                        user,
                        message,
                        type
                );


        // Find user's observer
        NotificationObserver observer =
                observers.get(user.getId());


        // Observer Pattern
        if (observer != null) {

            observer.update(notification);
        }
    }
}
