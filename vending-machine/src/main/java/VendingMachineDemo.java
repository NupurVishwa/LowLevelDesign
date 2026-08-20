import VendingMachine.VendingMachine;
import VendingMachine.Product;
import VendingMachine.Coin;git status
public class VendingMachineDemo {

    public static void main(String[] args) {

        VendingMachine machine =
                VendingMachine.getInstance();

        // Add products.
        Product chips =
                machine.addProduct(
                        "Chips",
                        1.5,
                        10
                );

        Product coke =
                machine.addProduct(
                        "Coke",
                        2.0,
                        5
                );

        // Customer selects Chips.
        machine.selectProduct(chips);

        // Customer inserts $1.
        machine.insertCoin(
                new Coin(1.0)
        );

        // Customer inserts another $1.
        machine.insertCoin(
                new Coin(1.0)
        );

        // Product can now be dispensed.
        machine.dispenseProduct();

        // Return $0.50 change.
        machine.returnChange();
    }
}