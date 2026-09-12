package Entities;

import Enums.TripStatus;
import Observer.TripObserver;
import State.RequestedState;
import State.TripState;

import java.util.ArrayList;
import java.util.List;

public class Trip {

    private final String id;
    private final User rider;

    private final Location pickupLocation;
    private final Location dropLocation;

    private final double distance;

    private Driver driver;

    private double fare;

    private TripStatus status;

    private TripState state;

    private final List<TripObserver> observers;

    public Trip(String id,
                User rider,
                Location pickupLocation,
                Location dropLocation,
                double distance) {

        this.id = id;
        this.rider = rider;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.distance = distance;

        this.status = TripStatus.REQUESTED;

        this.state = new RequestedState();

        this.observers = new ArrayList<>();
    }

    public void assignDriver(Driver driver) {
        state.assignDriver(this, driver);
    }

    public void startTrip() {
        state.startTrip(this);
    }

    public void completeTrip() {
        state.completeTrip(this);
    }

    public void addObserver(TripObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {

        for (TripObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getId() {
        return id;
    }

    public User getRider() {
        return rider;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDropLocation() {
        return dropLocation;
    }

    public double getDistance() {
        return distance;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getFare() {
        return fare;
    }

    public TripStatus getStatus() {
        return status;
    }

    public TripState getState() {
        return state;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setState(TripState state) {
        this.state = state;
    }

    @Override
    public String toString() {

        return "Trip{" +
                "id='" + id + '\'' +
                ", rider=" + rider.getName() +
                ", driver=" +
                (driver != null ? driver.getName() : "Not Assigned") +
                ", distance=" +
                String.format("%.2f", distance) +
                " km" +
                ", fare=" +
                String.format("%.2f", fare) +
                ", status=" +
                status +
                '}';
    }
}