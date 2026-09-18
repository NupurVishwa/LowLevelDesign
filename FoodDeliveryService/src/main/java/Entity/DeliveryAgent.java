package Entity;

import Observer.OrderObserver;
import Order.Order;

import java.util.concurrent.atomic.AtomicBoolean;

public class DeliveryAgent extends User implements OrderObserver {

    private static int nextId = 1;

    private final AtomicBoolean isAvailable = new AtomicBoolean(true);
    private Address currentLocation;
    private String id;

    // Constructor used by FoodDeliveryService
    public DeliveryAgent(String name, String phone, Address currentLocation) {
        super(name, phone);
        this.id = "DA" + nextId++;
        this.currentLocation = currentLocation;
    }

    // Constructor if you want to explicitly provide an ID
    public DeliveryAgent(String id, String name, String phone, Address currentLocation) {
        super(name, phone);
        this.id = id;
        this.currentLocation = currentLocation;
    }

    public void setAvailable(boolean available) {
        this.isAvailable.set(available);
    }

    public synchronized boolean isAvailable() {
        return isAvailable.get();
    }

    public String getId() {
        return id;
    }

    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Address getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void onUpdate(Order order) {
        System.out.printf(
                "--- Notification for Delivery Agent %s ---\n",
                getName()
        );

        System.out.printf(
                "  Order %s update: Status is %s.\n",
                order.getId(),
                order.getStatus()
        );

        System.out.println("-------------------------------------------\n");
    }
}