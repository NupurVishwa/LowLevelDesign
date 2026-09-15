package Model;
import Enum.ProductCategory;

import java.util.UUID;


// Product is an abstract class, so we cannot directly create a Product object.
// It acts as a common template for all products.
public abstract class Product {

    // Common properties that every Product will have.
    protected String id;
    protected String name;
    protected String description;
    protected double price;
    protected ProductCategory category;

    // Abstract methods define what every Product must provide.
    // The actual implementation will be given by the child class.
    public abstract String getId();
    public abstract String getName();
    public abstract String getDescription();
    public abstract double getPrice();
    public abstract ProductCategory getCategory();


    // Base implementation for the Builder
    // BaseProduct is the actual concrete implementation of Product.
    public static class BaseProduct extends Product {

        // Constructor receives all product information and stores it.
        // It is private because we want the Builder to control object creation.
        private BaseProduct(String id, String name, String description, double price, ProductCategory category) {

            // Store the provided ID in the Product's id field.
            this.id = id;

            // Store the product name.
            this.name = name;

            // Store the product description.
            this.description = description;

            // Store the product price.
            this.price = price;

            // Store the product category.
            this.category = category;
        }

        // Return the product ID.
        @Override
        public String getId() {
            return id;
        }

        // Return the product name.
        @Override
        public String getName() {
            return name;
        }

        // Return the product description.
        @Override
        public String getDescription() {
            return description;
        }

        // Return the product price.
        @Override
        public double getPrice() {
            return price;
        }

        // Return the product category.
        @Override
        public ProductCategory getCategory() {
            return category;
        }
    }


    // Builder Pattern for creating products.
    // Builder helps us create a Product step-by-step.
    public static class Builder {

        // Name and price are mandatory fields.
        private final String name;
        private final double price;

        // Description is optional, so it initially has an empty value.
        private String description = "";

        // Category is optional.
        private ProductCategory category;


        // Builder constructor receives the mandatory fields.
        public Builder(String name, double price) {

            // Store the provided product name.
            this.name = name;

            // Store the provided product price.
            this.price = price;
        }


        // Add description to the product.
        // 'this' returns the same Builder object so methods can be chained.
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }


        // Add category to the product.
        // 'this' allows us to continue chaining another method.
        public Builder withCategory(ProductCategory category) {
            this.category = category;
            return this;
        }


        // build() creates the final Product object.
        public Product build() {

            // Generate a unique ID automatically using UUID.
            // Then pass all collected information to BaseProduct.
            return new BaseProduct(UUID.randomUUID().toString(), name, description, price, category);
        }
    }
}
/***Flow to remember:**

        ```text
new Builder(name, price)
        ↓
withDescription(...)
        ↓
withCategory(...)
        ↓
build()
        ↓
Generate UUID
        ↓
Create BaseProduct
        ↓
Return Product
```

So the **Builder collects the data**, and `build()` **creates the actual Product object**.
 */
