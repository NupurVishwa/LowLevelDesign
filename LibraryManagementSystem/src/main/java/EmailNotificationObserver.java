public class EmailNotificationObserver implements NotificationObserver {

    @Override
    public void notify(Member member, String message) {

        System.out.println("\nEMAIL NOTIFICATION");

        System.out.println("To: " + member.getEmail());

        System.out.println("Message: " + message);
    }
}