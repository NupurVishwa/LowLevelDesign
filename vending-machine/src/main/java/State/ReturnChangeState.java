package State;
import VendingMachine.*;

public class ReturnChangeState implements VendingMachineState {

    private final VendingMachine machine;

    public ReturnChangeState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        throw new IllegalStateException(
                "Returning change"
        );
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalStateException(
                "Returning change"
        );
    }

    @Override
    public void insertNote(Note note) {
        throw new IllegalStateException(
                "Returning change"
        );
    }

    @Override
    public void dispenseProduct() {
        throw new IllegalStateException(
                "Product already dispensed"
        );
    }

    @Override
    public void returnChange() {

        double payment = machine.getTotalPayment();
        double price = machine.getSelectedProduct().getPrice();

        double change = payment - price;

        if (change > 0) {
            System.out.println(
                    "Returning change: $" + change
            );
        } else {
            System.out.println("No change.");
        }

        // Reset machine for next customer.
        machine.resetPayment();
        machine.setSelectedProduct(null);

        machine.setState(
                machine.getIdleState()
        );
    }
}