package Service;

import Model.Group;
import Model.Transaction;
import Model.User;
import Observer.BalanceNotificationObserver;
import Enum.SplitType;
import java.math.BigDecimal;
import java.util.Arrays;

public class SplitwiseDemo {

    public static void main(String[] args) {

        System.out.println(
                "========== SPLITWISE SYSTEM =========="
        );

        SplitwiseService service =
                SplitwiseService.getInstance();

        // Register observer
        service.addObserver(
                new BalanceNotificationObserver()
        );

        // ----------------------------------------
        // CREATE USERS
        // ----------------------------------------

        User john = service.addUser(
                "John",
                "john@example.com"
        );

        User jane = service.addUser(
                "Jane",
                "jane@example.com"
        );

        User bob = service.addUser(
                "Bob",
                "bob@example.com"
        );

        System.out.println("\nUsers created:");
        System.out.println(john.getName());
        System.out.println(jane.getName());
        System.out.println(bob.getName());

        // ----------------------------------------
        // CREATE GROUP
        // ----------------------------------------

        Group trip = service.createGroup(
                "Paris Trip"
        );

        trip.addMember(john);
        trip.addMember(jane);
        trip.addMember(bob);

        System.out.println(
                "\nGroup created: "
                        + trip.getName()
        );

        // ----------------------------------------
        // EQUAL SPLIT
        // ----------------------------------------

        System.out.println(
                "\n========== EQUAL SPLIT =========="
        );

        service.addExpense(
                "Dinner",
                new BigDecimal("120.00"),
                john,
                Arrays.asList(
                        john,
                        jane,
                        bob
                ),
                SplitType.EQUAL,
                null
        );

        // John paid ₹120
        // John share = ₹40
        // Jane owes John ₹40
        // Bob owes John ₹40

        // ----------------------------------------
        // EXACT SPLIT
        // ----------------------------------------

        System.out.println(
                "\n========== EXACT SPLIT =========="
        );

        service.addExpense(
                "Hotel",
                new BigDecimal("300.00"),
                jane,
                Arrays.asList(
                        john,
                        jane,
                        bob
                ),
                SplitType.EXACT,
                Arrays.asList(
                        new BigDecimal("100.00"),
                        new BigDecimal("100.00"),
                        new BigDecimal("100.00")
                )
        );

        // John owes Jane ₹100
        // Bob owes Jane ₹100

        // ----------------------------------------
        // PERCENTAGE SPLIT
        // ----------------------------------------

        System.out.println(
                "\n========== PERCENTAGE SPLIT =========="
        );

        service.addExpense(
                "Taxi",
                new BigDecimal("200.00"),
                bob,
                Arrays.asList(
                        john,
                        jane,
                        bob
                ),
                SplitType.PERCENTAGE,
                Arrays.asList(
                        new BigDecimal("50"),
                        new BigDecimal("30"),
                        new BigDecimal("20")
                )
        );

        // John = ₹100
        // Jane = ₹60
        // Bob = ₹40
        //
        // John owes Bob ₹100
        // Jane owes Bob ₹60

        // ----------------------------------------
        // SHOW BALANCES
        // ----------------------------------------

        service.printBalances(john);
        service.printBalances(jane);
        service.printBalances(bob);

        // ----------------------------------------
        // SETTLEMENT
        // ----------------------------------------

        System.out.println(
                "\n========== SETTLEMENT =========="
        );

        Transaction transaction =
                service.settleExpense(
                        jane,
                        john,
                        new BigDecimal("40.00")
                );

        System.out.println(
                "Transaction status: "
                        + transaction.getStatus()
        );

        service.printBalances(john);
        service.printBalances(jane);

        System.out.println(
                "\n========== END =========="
        );
    }
}