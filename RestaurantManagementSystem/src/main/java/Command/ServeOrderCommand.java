package Command;

import Model.Order;
import Model.Waiter;

public class ServeOrderCommand implements Command{
    private final Order order;
    private final Waiter waiter;

    public ServeOrderCommand(Order order, Waiter waiter) {
        this.order = order;
        this.waiter = waiter;
    }

    @Override
    public void execute() {
        waiter.serveOrder(order);
    }
}