package State;
import VendingMachine.VendingMachine;
import VendingMachine.VendingMachineState;
import VendingMachine.Coin;
import VendingMachine.Product;
import VendingMachine.Note;

public class ReadyState implements VendingMachineState {

    private final VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        throw new IllegalStateException(
                "Product already selected"
        );
    }

    @Override
    public void insertCoin(Coin coin) {

        machine.addPayment(coin.getValue());

        System.out.println(
                "Inserted coin: $" + coin.getValue()
        );

        checkPayment();
    }

    @Override
    public void insertNote(Note note) {

        machine.addPayment(note.getValue());

        System.out.println(
                "Inserted note: $" + note.getValue()
        );

        checkPayment();
    }

    private void checkPayment() {

        double payment = machine.getTotalPayment();
        double price = machine.getSelectedProduct().getPrice();

        System.out.println(
                "Total payment: $" + payment
        );

        if (payment >= price) {

            // Enough money inserted.
            machine.setState(machine.getDispenseState());

            System.out.println("Payment complete.");

        } else {

            System.out.println(
                    "Remaining: $" + (price - payment)
            );
        }
    }

    @Override
    public void dispenseProduct() {
        throw new IllegalStateException(
                "Insert sufficient money first"
        );
    }

    @Override
    public void returnChange() {

        double payment = machine.getTotalPayment();

        if (payment > 0) {
            System.out.println(
                    "Returning $" + payment
            );

            machine.resetPayment();
        }

        machine.setState(machine.getIdleState());
    }
}
