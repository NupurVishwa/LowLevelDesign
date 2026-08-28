import java.util.*;
public class NotificationService {
    private final Map<Integer,NotificationObserver> observers=new HashMap<>();
    private int id=1;
    public void subscribe(User u){
        observers.put(u.getId(),new UserNotificationObserver(u));
    }
    public void notifyUser(User u,NotificationType type,String message){
        Notification n=NotificationFactory.create(id++,u,message,type);
        NotificationObserver o=observers.get(u.getId()); if(o!=null)o.update(n);
    }
}
