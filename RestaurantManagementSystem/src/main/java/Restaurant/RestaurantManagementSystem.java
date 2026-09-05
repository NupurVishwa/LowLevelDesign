package Restaurant;

import Decorator.Bill;
import Decorator.BillComponent;
import Decorator.BaseBill;
import Decorator.ServiceChargeDecorator;
import Decorator.TaxDecorator;
import Model.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RestaurantManagementSystem {

    private static RestaurantManagementSystem instance;

    private final Restaurant restaurant;

    private final AtomicInteger orderIdCounter;
    private final AtomicInteger reservationIdCounter;

    private final Map<Integer, Order> orders;
    private final Map<Integer, Reservation> reservations;


    private RestaurantManagementSystem() {

        this.restaurant = Restaurant.getInstance();

        this.orderIdCounter = new AtomicInteger(1);
        this.reservationIdCounter = new AtomicInteger(1);

        this.orders = new HashMap<>();
        this.reservations = new HashMap<>();
    }


    // =====================================================
    // SINGLETON
    // =====================================================

    public static synchronized RestaurantManagementSystem getInstance() {

        if (instance == null) {
            instance = new RestaurantManagementSystem();
        }

        return instance;
    }


    // =====================================================
    // MENU MANAGEMENT
    // =====================================================

    public MenuItem addMenuItem(
            String id,
            String name,
            double price
    ) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "Menu item ID cannot be empty."
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Menu item name cannot be empty."
            );
        }

        if (price < 0) {
            throw new IllegalArgumentException(
                    "Menu item price cannot be negative."
            );
        }

        MenuItem menuItem =
                new MenuItem(id, name, price);

        restaurant.getMenu().addItem(menuItem);

        return menuItem;
    }


    // =====================================================
    // TABLE MANAGEMENT
    // =====================================================

    public Table addTable(
            int id,
            int capacity
    ) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Table capacity must be greater than zero."
            );
        }

        Table table =
                new Table(id, capacity);

        restaurant.addTable(table);

        return table;
    }


    public Table getTable(int tableId) {

        return restaurant.getTable(tableId);
    }


    // =====================================================
    // STAFF MANAGEMENT
    // =====================================================

    public Waiter addWaiter(
            String id,
            String name
    ) {

        Waiter waiter =
                new Waiter(id, name);

        restaurant.addWaiter(waiter);

        return waiter;
    }


    public Chef addChef(
            String id,
            String name
    ) {

        Chef chef =
                new Chef(id, name);

        restaurant.addChef(chef);

        return chef;
    }


    public Waiter getWaiter(
            String waiterId
    ) {

        return restaurant.getWaiter(waiterId);
    }


    public List<Chef> getChefs() {

        return restaurant.getChefs();
    }


    // =====================================================
    // ORDER MANAGEMENT
    // =====================================================

    public Order takeOrder(
            int tableId,
            String waiterId,
            List<String> menuItemIds
    ) {

        if (menuItemIds == null ||
                menuItemIds.isEmpty()) {

            throw new IllegalArgumentException(
                    "Order must contain at least one menu item."
            );
        }


        // Validate waiter

        Waiter waiter =
                restaurant.getWaiter(waiterId);

        if (waiter == null) {

            throw new IllegalArgumentException(
                    "Invalid waiter ID: " + waiterId
            );
        }


        // Validate table

        Table table =
                restaurant.getTable(tableId);

        if (table == null) {

            throw new IllegalArgumentException(
                    "Invalid table ID: " + tableId
            );
        }


        // Check availability

        if (!table.isAvailable()) {

            throw new IllegalStateException(
                    "Table is not available: " + tableId
            );
        }


        // Check chef

        if (restaurant.getChefs().isEmpty()) {

            throw new IllegalStateException(
                    "No chef available."
            );
        }


        // Reserve table

        table.reserve();


        // Create order

        Order order =
                new Order(
                        orderIdCounter.getAndIncrement(),
                        tableId
                );


        // Add order items

        for (String itemId : menuItemIds) {

            MenuItem menuItem =
                    restaurant.getMenu()
                            .getItem(itemId);

            if (menuItem == null) {

                table.release();

                throw new IllegalArgumentException(
                        "Invalid menu item ID: " + itemId
                );
            }


            OrderItem orderItem =
                    new OrderItem(
                            menuItem,
                            order
                    );


            // Observer Pattern

            orderItem.addObserver(waiter);


            order.addItem(orderItem);
        }


        orders.put(
                order.getOrderId(),
                order
        );


        // Start preparing

        order.markPreparing();


        return order;
    }


    public Order getOrder(
            int orderId
    ) {

        Order order =
                orders.get(orderId);

        if (order == null) {

            throw new IllegalArgumentException(
                    "Order not found: " + orderId
            );
        }

        return order;
    }


    public void markOrderPreparing(
            int orderId
    ) {

        Order order =
                getOrder(orderId);

        order.markPreparing();
    }


    public void markOrderReady(
            int orderId
    ) {

        Order order =
                getOrder(orderId);

        order.markReady();
    }


    public void markOrderServed(
            int orderId
    ) {

        Order order =
                getOrder(orderId);

        order.markServed();


        Table table =
                restaurant.getTable(
                        order.getTableId()
                );

        if (table != null) {
            table.release();
        }
    }


    // =====================================================
    // BILL MANAGEMENT
    // =====================================================

    public Bill getBill(
            int orderId
    ) {

        Order order =
                getOrder(orderId);


        BillComponent billComponent =
                new BaseBill(order);


        // 8% tax

        billComponent =
                new TaxDecorator(
                        billComponent,
                        0.08
                );


        // $5 service charge

        billComponent =
                new ServiceChargeDecorator(
                        billComponent,
                        5.00
                );


        return new Bill(
                billComponent
        );
    }


    // =====================================================
    // RESERVATION MANAGEMENT
    // =====================================================

    public Reservation makeReservation(
            String customerName,
            String customerPhone,
            int numberOfGuests,
            Timestamp reservationTime
    ) {

        Reservation reservation =
                new Reservation(
                        reservationIdCounter.getAndIncrement(),
                        customerName,
                        customerPhone,
                        numberOfGuests,
                        reservationTime
                );


        reservations.put(
                reservation.getId(),
                reservation
        );


        return reservation;
    }


    public Reservation getReservation(
            int reservationId
    ) {

        Reservation reservation =
                reservations.get(reservationId);

        if (reservation == null) {

            throw new IllegalArgumentException(
                    "Reservation not found: "
                            + reservationId
            );
        }

        return reservation;
    }
}