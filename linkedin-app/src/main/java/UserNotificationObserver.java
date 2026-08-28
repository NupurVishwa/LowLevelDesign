public class UserNotificationObserver implements NotificationObserver {
    private final User user;
    public UserNotificationObserver(User user){
        this.user=user;
    }
    public void update(Notification n){
        user.addNotification(n);
    }
}
