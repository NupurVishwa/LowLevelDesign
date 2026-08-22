import java.util.HashMap;
import java.util.Map;

public class IngredientStore {

    /*
     * Stores current quantity of every ingredient.
     *
     * Example:
     *
     * CoffeeBeans -> 100
     * Water       -> 500
     * Milk        -> 200
     */
    private final Map<String, Integer> ingredientLevels = new HashMap<>();


    // ---------------------------------------
    // Refill ingredient
    // ---------------------------------------

    public void refill(String ingredient, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        ingredientLevels.put(ingredient, ingredientLevels.getOrDefault(ingredient, 0) + quantity);

        System.out.println(ingredient + " refilled by " + quantity);
    }


    // ---------------------------------------
    // Check whether all ingredients exist
    // ---------------------------------------

    public boolean hasIngredients(
            Map<String, Integer> requiredIngredients) {

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {

            String ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();

            int availableQuantity = ingredientLevels.getOrDefault(ingredient, 0);

            if (availableQuantity < requiredQuantity) {
                return false;
            }
        }

        return true;
    }


    // ---------------------------------------
    // Consume ingredients
    // ---------------------------------------

    public void useIngredients(
            Map<String, Integer> requiredIngredients) {

        /*
         * We should call hasIngredients() first.
         * Therefore we know enough ingredients exist.
         */

        if (!hasIngredients(requiredIngredients)) {
            throw new IllegalStateException("Insufficient ingredients");
        }

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {

            String ingredient = entry.getKey();
            int quantity = entry.getValue();

            ingredientLevels.put(ingredient, ingredientLevels.get(ingredient) - quantity);
        }
    }


    // ---------------------------------------
    // Get current quantity
    // ---------------------------------------

    public int getLevel(String ingredient) {

        return ingredientLevels.getOrDefault(
                ingredient, 0
        );
    }
}
