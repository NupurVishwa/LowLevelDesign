public class NotificationFactory {
    public static Notification create(int id,User recipient,String message,NotificationType type){
        return new Notification(id,recipient,message,type);
    }
}
