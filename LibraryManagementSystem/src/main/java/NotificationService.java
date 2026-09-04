import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private final List<NotificationObserver> observers;


    public NotificationService() {

        observers = new ArrayList<>();
    }


    public void addObserver(NotificationObserver observer) {

        observers.add(observer);
    }

    public void notifyMember(Member member, String message) {
        for (NotificationObserver observer : observers) {
            observer.notify(member, message);
        }
    }
}