import java.util.Map;
import java.util.TreeMap;

class CashDispenser {

    private final Map<Integer, Integer> cashInventory = new TreeMap<>(java.util.Collections.reverseOrder());

    public CashDispenser() {
        cashInventory.put(500, 10);
        cashInventory.put(200, 10);
        cashInventory.put(100, 20);
    }

    public synchronized boolean canDispense(int amount) {

        int remaining = amount;

        for (Map.Entry<Integer, Integer> entry : cashInventory.entrySet()) {

            int denomination = entry.getKey();
            int availableNotes = entry.getValue();

            int requiredNotes = Math.min(remaining / denomination, availableNotes);

            remaining -= requiredNotes * denomination;
        }

        return remaining == 0;
    }

    public synchronized void dispenseCash(int amount) {

        if (!canDispense(amount)) {
            throw new IllegalStateException("ATM does not have required denominations");
        }

        int remaining = amount;

        for (Map.Entry<Integer, Integer> entry : cashInventory.entrySet()) {

            int denomination = entry.getKey();
            int availableNotes = entry.getValue();

            int notesToDispense = Math.min(remaining / denomination, availableNotes);

            if (notesToDispense > 0) {

                cashInventory.put(denomination, availableNotes - notesToDispense);

                remaining -= notesToDispense * denomination;

                System.out.println("Dispensing " + notesToDispense + " x ₹" + denomination);
            }
        }
    }

    public synchronized void addCash(int denomination, int numberOfNotes) {

        cashInventory.merge(denomination, numberOfNotes, Integer::sum);
    }
}