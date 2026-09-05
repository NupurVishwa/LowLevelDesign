package Restaurant;

import Command.Command;
import Command.PrepareOrderCommand;
import Command.ServeOrderCommand;
import Decorator.Bill;
import Model.*;

import java.sql.Timestamp;
import java.util.List;

public class RestaurantManagementSystemFacade {

    private static RestaurantManagementSystemFacade instance;

    private final RestaurantManagementSystem system;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    private RestaurantManagementSystemFacade() {
        this.system = RestaurantManagementSystem.getInstance();
    }


    // =====================================================
    // SINGLETON
    // =====================================================

    public static synchronized RestaurantManagementSystemFacade getInstance() {

        if (instance == null) {
            instance = new RestaurantManagementSystemFacade();
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

        return system.addMenuItem(
                id,
                name,
                price
        );
    }



    // =====================================================
    // TABLE MANAGEMENT
    // =====================================================

    public Table addTable(
            int id,
            int capacity
    ) {

        return system.addTable(
                id,
                capacity
        );
    }


    public Table getTable(
            int tableId
    ) {

        return system.getTable(tableId);
    }


    // =====================================================
    // STAFF MANAGEMENT
    // =====================================================

    public Waiter addWaiter(
            String id,
            String name
    ) {

        return system.addWaiter(
                id,
                name
        );
    }


    public Chef addChef(
            String id,
            String name
    ) {

        return system.addChef(
                id,
                name
        );
    }


    public Waiter getWaiter(
            String waiterId
    ) {

        return system.getWaiter(waiterId);
    }


    public List<Chef> getChefs() {

        return system.getChefs();
    }


    // =====================================================
    // ORDER MANAGEMENT
    // =====================================================

    public Order takeOrder(
            int tableId,
            String waiterId,
            List<String> menuItemIds
    ) {

        /*
         * RestaurantManagementSystem handles:
         * - Table validation
         * - Waiter validation
         * - Menu validation
         * - Order creation
         * - Observer registration
         * - Order storage
         */

        Order order =
                system.takeOrder(
                        tableId,
                        waiterId,
                        menuItemIds
                );


        /*
         * Command Pattern
         *
         * Facade finds a chef and sends the command.
         */

        Chef chef =
                system.getChefs()
                        .stream()
                        .findFirst()
                        .orElseThrow(
                                () -> new IllegalStateException(
                                        "No chef available."
                                )
                        );


        Command prepareOrderCommand =
                new PrepareOrderCommand(
                        order,
                        chef
                );


        prepareOrderCommand.execute();


        return order;
    }


    public Order getOrder(
            int orderId
    ) {

        return system.getOrder(orderId);
    }


    // =====================================================
    // ORDER STATUS
    // =====================================================

    public void markOrderPreparing(
            int orderId
    ) {

        system.markOrderPreparing(orderId);
    }


    public void markItemsAsReady(
            int orderId
    ) {

        Order order =
                system.getOrder(orderId);


        System.out.println(
                "\nChef has finished preparing order "
                        + order.getOrderId()
        );


        system.markOrderReady(orderId);
    }


    // =====================================================
    // SERVE ORDER
    // =====================================================

    public void serveOrder(
            String waiterId,
            int orderId
    ) {

        Order order =
                system.getOrder(orderId);


        Waiter waiter =
                system.getWaiter(waiterId);


        /*
         * Command Pattern
         *
         * ServeOrderCommand handles serving the order.
         */

        Command serveOrderCommand =
                new ServeOrderCommand(
                        order,
                        waiter
                );


        serveOrderCommand.execute();
    }


    // =====================================================
    // BILL MANAGEMENT
    // =====================================================

    public Bill generateBill(
            int orderId
    ) {

        return system.getBill(orderId);
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

        return system.makeReservation(
                customerName,
                customerPhone,
                numberOfGuests,
                reservationTime
        );
    }


    public Reservation getReservation(
            int reservationId
    ) {

        return system.getReservation(
                reservationId
        );
    }
}