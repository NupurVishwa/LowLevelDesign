package Model;

import Model.Chef;
import Model.Menu;
import Model.Table;
import Model.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private static Restaurant instance;

    private final Menu menu;
    private final List<Table> tables;
    private final List<Waiter> waiters;
    private final List<Chef> chefs;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    private Restaurant() {

        this.menu = new Menu();
        this.tables = new ArrayList<>();
        this.waiters = new ArrayList<>();
        this.chefs = new ArrayList<>();
    }


    // =====================================================
    // SINGLETON
    // =====================================================

    public static synchronized Restaurant getInstance() {

        if (instance == null) {
            instance = new Restaurant();
        }

        return instance;
    }


    // =====================================================
    // MENU
    // =====================================================

    public Menu getMenu() {

        return menu;
    }


    // =====================================================
    // TABLE
    // =====================================================

    public void addTable(Table table) {

        if (table == null) {
            throw new IllegalArgumentException(
                    "Table cannot be null."
            );
        }

        tables.add(table);
    }


    public Table getTable(int tableId) {

        for (Table table : tables) {

            if (table.getId() == tableId) {
                return table;
            }
        }

        return null;
    }


    public List<Table> getTables() {

        return tables;
    }


    // =====================================================
    // WAITER
    // =====================================================

    public void addWaiter(Waiter waiter) {

        if (waiter == null) {
            throw new IllegalArgumentException(
                    "Waiter cannot be null."
            );
        }

        waiters.add(waiter);
    }


    public Waiter getWaiter(String waiterId) {

        for (Waiter waiter : waiters) {

            if (waiter.getId().equals(waiterId)) {
                return waiter;
            }
        }

        return null;
    }


    public List<Waiter> getWaiters() {

        return waiters;
    }


    // =====================================================
    // CHEF
    // =====================================================

    public void addChef(Chef chef) {

        if (chef == null) {
            throw new IllegalArgumentException(
                    "Chef cannot be null."
            );
        }

        chefs.add(chef);
    }


    public List<Chef> getChefs() {

        return chefs;
    }
}