public class Dispenser {

    public void dispense(String coffeeName) {

        /*
         * In a real vending machine this class
         * would control the physical dispenser.
         *
         * For our LLD, we simply simulate it.
         */

        System.out.println("Dispensing " + coffeeName + "...");

        System.out.println(coffeeName + " is ready!");
    }
}
