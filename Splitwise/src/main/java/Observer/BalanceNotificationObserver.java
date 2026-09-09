package Observer;

import Model.Expense;
import Model.Split;

public class BalanceNotificationObserver
        implements BalanceObserver {

    @Override
    public void onBalanceUpdated(Expense expense) {

        System.out.println(
                "\n[Notification] Expense added: "
                        + expense.getDescription()
        );

        for (Split split : expense.getSplits()) {

            System.out.println(
                    split.getUser().getName()
                            + " owes ₹"
                            + split.getAmount()
            );
        }
    }
}