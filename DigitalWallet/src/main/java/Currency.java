public enum Currency {

    USD("$"),
    EUR("€"),
    INR("₹"),
    GBP("£");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return name();
    }

    public String getSymbol() {
        return symbol;
    }
}