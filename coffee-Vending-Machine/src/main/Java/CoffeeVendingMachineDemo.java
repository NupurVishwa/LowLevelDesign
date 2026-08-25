import java.util.Map;

public class CoffeeVendingMachineDemo {

    public static void main(String[] args) {

        CoffeeVendingMachine machine =
                new CoffeeVendingMachine();


        // ==========================================
        // 1. Add Espresso recipe
        // ==========================================

        CoffeeRecipe espresso = new CoffeeRecipe("Espresso", Map.of("CoffeeBeans", 10, "Water", 30), 40);

        machine.addRecipe(espresso);


        // ==========================================
        // 2. Add Latte recipe
        // ==========================================

        CoffeeRecipe latte = new CoffeeRecipe("Latte", Map.of("CoffeeBeans", 10, "Water", 30, "Milk", 50), 60);

        machine.addRecipe(latte);


        // ==========================================
        // 3. Refill ingredients
        // ==========================================

        machine.refillIngredient("CoffeeBeans", 100);
        machine.refillIngredient("Water", 500);
        machine.refillIngredient("Milk", 200);


        // ==========================================
        // 4. Buy Espresso
        // ==========================================

        Payment payment = new Payment(50, PaymentType.CASH);

        machine.makeCoffee("Espresso", payment);


        // ==========================================
        // 5. Check remaining inventory
        // ==========================================

        System.out.println("Coffee Beans remaining: " + machine.getIngredientLevel("CoffeeBeans"));

        System.out.println("Water remaining: " + machine.getIngredientLevel("Water"));
    }
}
