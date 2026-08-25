package State;

import VendingMachine.VendingMachine;
import VendingMachine.VendingMachineState;
import VendingMachine.Coin;
import VendingMachine.Product;
import VendingMachine.Note;

public class IdleState implements VendingMachineState {

    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {

        // Product must exist in inventory.
        if (!machine.getInventory().isAvailable(product)) {
            throw new RuntimeException("Product is out of stock");
        }

        machine.setSelectedProduct(product);

        System.out.println("Selected: " + product.getName());

        // Move to Ready state.
        machine.setState(machine.getReadyState());
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalStateException(
                "Select product first"
        );
    }

    @Override
    public void insertNote(Note note) {
        throw new IllegalStateException(
                "Select product first"
        );
    }

    @Override
    public void dispenseProduct() {
        throw new IllegalStateException(
                "Select product first"
        );
    }

    @Override
    public void returnChange() {
        throw new IllegalStateException(
                "No money inserted"
        );
    }
}