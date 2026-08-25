import java.util.HashMap;
import java.util.Map;

public class CoffeeVendingMachine {

    /*
     * Machine owns the ingredient inventory.
     */
    private final IngredientStore ingredientStore;

    /*
     * Machine uses a dispenser to prepare coffee.
     */
    private final Dispenser dispenser;

    /*
     * Stores all available coffee recipes.
     *
     * Example:
     *
     * "Espresso" -> Espresso recipe
     * "Latte"    -> Latte recipe
     * "Mocha"    -> Mocha recipe
     */
    private final Map<String, CoffeeRecipe> recipes;


    public CoffeeVendingMachine() {

        ingredientStore = new IngredientStore();
        dispenser = new Dispenser();
        recipes = new HashMap<>();
    }


    // ==========================================
    // Add a new coffee recipe
    // ==========================================

    public void addRecipe(CoffeeRecipe recipe) {
        recipes.put(recipe.getName(), recipe);

        System.out.println("Added recipe: " + recipe.getName());
    }


    // ==========================================
    // Refill ingredient
    // ==========================================

    public void refillIngredient(String ingredient, int quantity) {

        ingredientStore.refill(ingredient, quantity);
    }


    // ==========================================
    // Make Coffee
    // ==========================================

    public void makeCoffee(String coffeeName, Payment payment) {

        // --------------------------------------
        // Step 1: Find recipe
        // --------------------------------------

        CoffeeRecipe recipe = recipes.get(coffeeName);

        if (recipe == null) {

            throw new IllegalArgumentException("Coffee not available: "+ coffeeName);
        }


        // --------------------------------------
        // Step 2: Check payment amount
        // --------------------------------------

        if (payment.getAmount() < recipe.getPrice()) {

            throw new IllegalStateException("Insufficient payment. " + "Price = " + recipe.getPrice());
        }


        // --------------------------------------
        // Step 3: Check ingredients
        // --------------------------------------

        if (!ingredientStore.hasIngredients(recipe.getIngredients())) {

            throw new IllegalStateException("Insufficient ingredients");
        }


        // --------------------------------------
        // Step 4: Process payment
        // --------------------------------------

        PaymentProcessor paymentProcessor = PaymentProcessorFactory.getProcessor(payment.getPaymentType());


        boolean paymentSuccessful = paymentProcessor.PaymentProcessor(payment);

        if (!paymentSuccessful) {

            throw new IllegalStateException("Payment failed");
        }


        // --------------------------------------
        // Step 5: Consume ingredients
        // --------------------------------------

        ingredientStore.useIngredients(recipe.getIngredients());


        // --------------------------------------
        // Step 6: Dispense coffee
        // --------------------------------------

        dispenser.dispense(recipe.getName());


        // --------------------------------------
        // Step 7: Calculate change
        // --------------------------------------

        double change = payment.getAmount() - recipe.getPrice();

        if (change > 0) {

            System.out.println("Returning change: $" + change);
        }
    }


    // ==========================================
    // Get ingredient level
    // ==========================================

    public int getIngredientLevel(
            String ingredient) {

        return ingredientStore.getLevel(
                ingredient
        );
    }
}