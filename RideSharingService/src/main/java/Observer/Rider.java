package Observer;

import Entities.Trip;
import Entities.User;

public class Rider implements TripObserver {

    private final User user;

    public Rider(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void update(Trip trip) {

        System.out.println(
                "Notification to " + user.getName()
                        + ": Trip " + trip.getId()
                        + " status changed to "
                        + trip.getStatus()
        );
    }
}