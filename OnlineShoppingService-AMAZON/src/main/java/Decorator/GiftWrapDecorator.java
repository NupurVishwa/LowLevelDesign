package Decorator;

import Model.Product;

// GiftWrapDecorator adds gift-wrapping functionality to an existing Product.
// It extends ProductDecorator, so it can wrap another Product.
public class GiftWrapDecorator extends ProductDecorator {

    // Extra cost added when the product is gift wrapped.
    private static final double GIFT_WRAP_COST = 5.00;


    // Constructor receives the Product that we want to gift wrap.
    public GiftWrapDecorator(Product product) {

        // Pass the Product to the parent ProductDecorator.
        // The parent stores it in decoratedProduct.
        super(product);
    }


    // Override getPrice() because gift wrapping increases the price.
    @Override
    public double getPrice() {

        // Get the original product price using the parent implementation.
        // Then add the gift wrapping cost.
        return super.getPrice() + GIFT_WRAP_COST;
    }


    // Override getDescription() to show that the product is gift wrapped.
    @Override
    public String getDescription() {

        // Get the original description and append "(Gift Wrapped)".
        return super.getDescription() + " (Gift Wrapped)";
    }
}

/*Flow

Original Product
      ↓
GiftWrapDecorator
      ↓
Price = Original Price + $5
Description = Original Description + "(Gift Wrapped)"



For example:

Product product = new Product.Builder("Book", 20).build();

Product giftWrapped = new GiftWrapDecorator(product);


Result:text
Price       → $25
Description → Original Description (Gift Wrapped)


Key point:** The original `Product` is not modified. The decorator **wraps it and adds new behavior**.

 */
