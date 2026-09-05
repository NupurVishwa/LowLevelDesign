package State;

import Model.OrderItem;

public class PreparingState implements OrderItemState {
    @Override
    public void next(OrderItem item) {
        item.setState(new ReadyForPickupState());
    }

    @Override
    public void prev(OrderItem item) {
        item.setState(new OrderedState());
    }

    @Override
    public String getStatus() { return "PREPARING"; }
}