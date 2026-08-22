import java.util.Map;

public class CoffeeRecipe {

    private final String name;

    /*
     * Example:
     *
     * Espresso:
     * CoffeeBeans -> 10
     * Water       -> 30
     *
     * Latte:
     * CoffeeBeans -> 10
     * Water       -> 30
     * Milk        -> 50
     */
    private final Map<String, Integer> ingredients;

    private final double price;

    public CoffeeRecipe(String name, Map<String, Integer> ingredients, double price) {

        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }
}