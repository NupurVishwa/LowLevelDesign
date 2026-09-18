package Entity;

import Observer.OrderObserver;
import Order.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements OrderObserver {

    private Address address;
    private final List<Order> orderHistory = new ArrayList<>();
    private String id;

    // Constructor used by FoodDeliveryService
    public Customer(String name, String phone, Address address) {
        super(name, phone);
        this.id = "C" + System.currentTimeMillis();
        this.address = address;
    }

    // Constructor if you want to explicitly provide an ID
    public Customer(String id, String name, String phone, Address address) {
        super(name, phone);
        this.id = id;
        this.address = address;
    }

    public void addOrderToHistory(Order order) {
        this.orderHistory.add(order);
    }

    public Address getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    @Override
    public void onUpdate(Order order) {
        System.out.printf(
                "--- Notification for Customer %s ---\n",
                getName()
        );

        System.out.printf(
                "  Order %s is now %s.\n",
                order.getId(),
                order.getStatus()
        );

        System.out.println("-------------------------------------\n");
    }
}