package Decorator;

public class AmenityDecorator implements Bookable{
    private final Bookable bookable;

    public AmenityDecorator(Bookable bookable) {
        this.bookable = bookable;
    }
    @Override
    public double getCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }
}
