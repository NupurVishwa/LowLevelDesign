package State;

import Model.OrderItem;

public interface OrderItemState {
    void next(OrderItem item);
    void prev(OrderItem item);
    String getStatus();
}