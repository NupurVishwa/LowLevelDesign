
package Model;

public class Table {

    private final int id;
    private final int tableNumber;
    private final int capacity;
    private boolean available;

    public Table(int id, int capacity) {
        this.id = id;
        this.tableNumber = id;
        this.capacity = capacity;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void reserve() {
        if (!available) {
            throw new IllegalStateException(
                    "Table " + tableNumber + " is already reserved."
            );
        }

        available = false;
    }

    public void release() {
        available = true;
    }
}
