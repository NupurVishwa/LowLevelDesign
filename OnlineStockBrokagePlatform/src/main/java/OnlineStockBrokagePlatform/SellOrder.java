package OnlineStockBrokagePlatform;

import Entities.Account;
import Entities.Order;
import Entities.Stock;
import Entities.User;
import Enum.OrderStatus;
import Enum.OrderType;
import Exceptions.InsufficientStockException;
import Strategy.ExecutionStrategy;

public class SellOrder extends Order {

    public SellOrder(
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

        // Check if user has enough stocks
        int availableQuantity =
                account.getStockQuantity(getStock().getSymbol());

        if (availableQuantity < getQuantity()) {
            setStatus(OrderStatus.FAILED);

            throw new InsufficientStockException(
                    "Not enough " + getStock().getSymbol()
                            + " stock to execute the sell order."
            );
        }

        // Remove stocks from portfolio
        account.removeStock(
                getStock().getSymbol(),
                getQuantity()
        );

        // Calculate money received from selling
        double totalAmount = getQuantity() * getPrice();

        // Add money to user's account
        account.credit(totalAmount);

        // Mark order as filled
        setStatus(OrderStatus.FILLED);
    }
}

/*

        ### Your Buy/Sell orders now follow the same structure

```text
        User
 │
         └── Account
      ├── balance
      └── portfolio

        BuyOrder
 ├── check execution strategy
 ├── debit money
 ├── add stocks
 └── FILLED

        SellOrder
 ├── check execution strategy
 ├── remove stocks
 ├── credit money
 └── FILLED
```

One important detail: both currently use:

        ```java
OrderType.LIMIT
```

because your `Order` has a limit-price field. If your demo creates **MARKET orders too**, we'll need to update the constructors/demo to pass `OrderType.MARKET` appropriately.

 */
