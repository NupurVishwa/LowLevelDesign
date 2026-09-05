package Restaurant;

import Decorator.Bill;
import Model.MenuItem;
import Model.Order;
import Model.Reservation;

import java.sql.Timestamp;
import java.util.List;

public class RestaurantManagementSystemDemo {

    public static void main(String[] args) {

        run();
    }


    public static void run() {

        RestaurantManagementSystemFacade restaurantSystem =
                RestaurantManagementSystemFacade.getInstance();


        System.out.println(
                "======================================"
        );

        System.out.println(
                " RESTAURANT MANAGEMENT SYSTEM DEMO"
        );

        System.out.println(
                "======================================"
        );


        // =================================================
        // 1. ADD TABLES
        // =================================================

        restaurantSystem.addTable(
                1,
                4
        );

        restaurantSystem.addTable(
                2,
                2
        );

        System.out.println(
                "\nTables added successfully."
        );


        // =================================================
        // 2. ADD STAFF
        // =================================================

        restaurantSystem.addWaiter(
                "W1",
                "Alice"
        );

        restaurantSystem.addChef(
                "C1",
                "Bob"
        );

        System.out.println(
                "Waiter and Chef added successfully."
        );


        // =================================================
        // 3. ADD MENU ITEMS
        // =================================================

        MenuItem burger =
                restaurantSystem.addMenuItem(
                        "M1",
                        "Burger",
                        9.99
                );


        MenuItem pizza =
                restaurantSystem.addMenuItem(
                        "M2",
                        "Pizza",
                        12.99
                );


        MenuItem salad =
                restaurantSystem.addMenuItem(
                        "M3",
                        "Salad",
                        7.99
                );


        System.out.println(
                "Menu items added successfully."
        );


        // =================================================
        // 4. TAKE ORDER
        // =================================================

        Order order =
                restaurantSystem.takeOrder(
                        1,
                        "W1",
                        List.of(
                                "M1",
                                "M3"
                        )
                );


        System.out.println(
                "\nOrder placed successfully."
        );


        System.out.println(
                "Order ID: "
                        + order.getOrderId()
        );


        System.out.println(
                "Order Status: "
                        + order.getStatus()
        );


        // =================================================
        // 5. MARK ORDER READY
        // =================================================

        restaurantSystem.markItemsAsReady(
                order.getOrderId()
        );


        System.out.println(
                "Order Status: "
                        + order.getStatus()
        );


        // =================================================
        // 6. SERVE ORDER
        // =================================================

        restaurantSystem.serveOrder(
                "W1",
                order.getOrderId()
        );


        System.out.println(
                "Order served successfully."
        );


        System.out.println(
                "Order Status: "
                        + order.getStatus()
        );


        // =================================================
        // 7. GENERATE BILL
        // =================================================

        Bill bill =
                restaurantSystem.generateBill(
                        order.getOrderId()
                );


        System.out.println(
                "\n===== BILL ====="
        );


        System.out.printf(
                "Total Amount: $%.2f%n",
                bill.getTotalAmount()
        );


        bill.printBill();


        // =================================================
        // 8. MAKE RESERVATION
        // =================================================

        Reservation reservation =
                restaurantSystem.makeReservation(
                        "John Doe",
                        "9876543210",
                        4,
                        new Timestamp(
                                System.currentTimeMillis()
                        )
                );


        System.out.println(
                "\nReservation created successfully."
        );


        System.out.println(
                "Reservation ID: "
                        + reservation.getId()
        );


        System.out.println(
                "Customer: "
                        + reservation.getCustomerName()
        );


        System.out.println(
                "Guests: "
                        + reservation.getNumberOfGuests()
        );


        // =================================================
        // COMPLETED
        // =================================================

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                " DEMO COMPLETED SUCCESSFULLY"
        );

        System.out.println(
                "======================================"
        );
    }
}