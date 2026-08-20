package VendingMachine;
import State.DispenseState;
import State.IdleState;
import State.ReadyState;
import State.ReturnChangeState;


public class VendingMachine {

    // Singleton instance.
    private static VendingMachine instance;

    private final Inventory inventory;

    // Different states of the vending machine.
    private final VendingMachineState idleState;
    private final VendingMachineState readyState;
    private final VendingMachineState dispenseState;
    private final VendingMachineState returnChangeState;

    // Current state of the machine.
    private VendingMachineState currentState;

    // Product selected by the customer.
    private Product selectedProduct;

    // Total money inserted by the customer.
    private double totalPayment;

    // Private constructor because of Singleton Pattern.
    private VendingMachine() {

        inventory = new Inventory();

        // Create all states.
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispenseState = new DispenseState(this);
        returnChangeState = new ReturnChangeState(this);

        // Machine starts in Idle state.
        currentState = idleState;
    }

    // Singleton: only one vending machine object.
    public static VendingMachine getInstance() {

        if (instance == null) {
            instance = new VendingMachine();
        }

        return instance;
    }

    // -----------------------------
    // Product management
    // -----------------------------

    public Product addProduct(
            String name,
            double price,
            int quantity) {

        Product product = new Product(name, price);

        inventory.addProduct(product, quantity);

        return product;
    }

    // -----------------------------
    // State management
    // -----------------------------

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getReadyState() {
        return readyState;
    }

    public VendingMachineState getDispenseState() {
        return dispenseState;
    }

    public VendingMachineState getReturnChangeState() {
        return returnChangeState;
    }

    // -----------------------------
    // Delegate operations to state
    // -----------------------------

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        currentState.insertNote(note);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    // -----------------------------
    // Getters / setters
    // -----------------------------

    public Inventory getInventory() {
        return inventory;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void addPayment(double amount) {
        totalPayment += amount;
    }

    public void resetPayment() {
        totalPayment = 0;
    }
}
