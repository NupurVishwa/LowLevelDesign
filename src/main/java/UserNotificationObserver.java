public class UserNotificationObserver
        implements NotificationObserver {

    private User user;


    public UserNotificationObserver(User user) {

        this.user = user;
    }


    @Override
    public void update(Notification notification) {

        user.addNotification(notification);

        System.out.println(
                "Notification sent to "
                        + user.getName()
        );
    }
}