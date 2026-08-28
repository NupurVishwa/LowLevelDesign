package State;
import VendingMachine.*;

public class DispenseState implements VendingMachineState {

    private final VendingMachine machine;

    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        throw new IllegalStateException(
                "Currently dispensing product"
        );
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalStateException(
                "Currently dispensing product"
        );
    }

    @Override
    public void insertNote(Note note) {
        throw new IllegalStateException(
                "Currently dispensing product"
        );
    }

    @Override
    public void dispenseProduct() {

        Product product = machine.getSelectedProduct();

        // Remove product from inventory.
        machine.getInventory().reduceQuantity(product);

        System.out.println(
                "Dispensing: " + product.getName()
        );

        // Move to change state.
        machine.setState(
                machine.getReturnChangeState()
        );
    }

    @Override
    public void returnChange() {
        throw new IllegalStateException(
                "Dispense product first"
        );
    }
}
