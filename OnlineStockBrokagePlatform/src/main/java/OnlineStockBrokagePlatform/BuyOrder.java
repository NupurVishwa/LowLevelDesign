package OnlineStockBrokagePlatform;

import Entities.Account;
import Entities.Order;
import Entities.Stock;
import Entities.User;
import Enum.OrderStatus;
import Enum.OrderType;
import Exceptions.InsufficientFundsException;
import Strategy.ExecutionStrategy;

public class BuyOrder extends Order {

    public BuyOrder(
            String orderId,
            User user,
            Stock stock,
            int quantity,
            double price,
            ExecutionStrategy strategy
    ) {
        super(
                orderId,
                user,
                stock,
                OrderType.LIMIT,
                quantity,
                price,
                strategy,
                user
        );
    }

    public void execute(double marketPrice) {

        // Check whether the order can be executed
        if (!getExecutionStrategy().canExecute(this, marketPrice)) {
            return;
        }

        // Get user's account
        Account account = getUser().getAccount();

        // Calculate total cost
        double totalCost = getQuantity() * getPrice();

        // Check if user has enough money
        if (account.getBalance() < totalCost) {
            setStatus(OrderStatus.FAILED);

            throw new InsufficientFundsException(
                    "Insufficient funds to execute the buy order."
            );
        }

        // Deduct money from account
        account.debit(totalCost);

        // Add stocks to portfolio
        account.addStock(
                getStock().getSymbol(),
                getQuantity()
        );

        // Mark order as filled
        setStatus(OrderStatus.FILLED);
    }
}

/*

        **Important:** This version fixes all the errors you posted:

        * `Account` → `User`
        * private fields → getters
* `withdraw()` → `debit()`
        * portfolio update → `addStock()`
        * `EXECUTED` → `FILLED`
        * `REJECTED` → `FAILED`
        * removes the invalid `@Override`
        * uses your current `Order` constructor.

 */
