package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<Product, Integer> productQuantities = new HashMap<>();

    // Add product to inventory.
    public void addProduct(Product product, int quantity) {
        productQuantities.put(
                product,
                productQuantities.getOrDefault(product, 0) + quantity
        );
    }

    // Check how many products are available.
    public int getQuantity(Product product) {
        return productQuantities.getOrDefault(product, 0);
    }

    // Check whether product is available.
    public boolean isAvailable(Product product) {
        return getQuantity(product) > 0;
    }

    // Remove one product after successful purchase.
    public void reduceQuantity(Product product) {

        if (!isAvailable(product)) {
            throw new RuntimeException("Product is out of stock");
        }

        productQuantities.put(
                product,
                getQuantity(product) - 1
        );
    }
}
