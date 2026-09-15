package Decorator;

import Model.Product;
import Enum.ProductCategory;

// ProductDecorator is an abstract class that follows the Decorator Pattern.
// It extends Product, so it can be used wherever a Product is expected.
public abstract class ProductDecorator extends Product {

    // Stores the original Product that we want to decorate/enhance.
    protected Product decoratedProduct;


    // Constructor receives the Product that we want to wrap.
    public ProductDecorator(Product product) {

        // Store the given Product inside the decorator.
        this.decoratedProduct = product;
    }


    // Return the ID of the original/decorated product.
    @Override
    public String getId() {
        return decoratedProduct.getId();
    }


    // Return the name of the original/decorated product.
    @Override
    public String getName() {
        return decoratedProduct.getName();
    }


    // Return the price of the original/decorated product.
    // A child decorator can override this to add extra cost.
    @Override
    public double getPrice() {
        return decoratedProduct.getPrice();
    }


    // Return the description of the original/decorated product.
    // A child decorator can override this to add extra information.
    @Override
    public String getDescription() {
        return decoratedProduct.getDescription();
    }


    // Return the category of the original/decorated product.
    @Override
    public ProductCategory getCategory() {
        return decoratedProduct.getCategory();
    }
}
/*
### Simple idea



Original Product
      ↓
ProductDecorator
      ↓
Extra feature / behavior


For example, if you have:


Product laptop = new Product.Builder("Laptop", 80000).build();


You can wrap it:

        ```text
        Laptop
  ↓
WarrantyDecorator
  ↓
GiftWrapDecorator
```

Each decorator **wraps the previous Product** and can modify things like **price or description** without changing the original `Product` class.
 **In short:** `ProductDecorator` provides the base structure for adding extra features to a Product dynamically.


 */
