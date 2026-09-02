import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private static final CurrencyConverter INSTANCE =
            new CurrencyConverter();

    private final Map<String, BigDecimal> exchangeRates;

    private CurrencyConverter() {

        exchangeRates = new HashMap<>();

        // Rates relative to USD
        exchangeRates.put("USD", BigDecimal.ONE);
        exchangeRates.put("EUR", new BigDecimal("0.92"));
        exchangeRates.put("GBP", new BigDecimal("0.79"));
        exchangeRates.put("INR", new BigDecimal("83.00"));
    }

    public static CurrencyConverter getInstance() {
        return INSTANCE;
    }

    public BigDecimal convert(
            BigDecimal amount,
            Currency from,
            Currency to
    ) {

        if (from == to) {
            return amount;
        }

        BigDecimal fromRate =
                exchangeRates.get(from.getCode());

        BigDecimal toRate =
                exchangeRates.get(to.getCode());

        BigDecimal usdAmount =
                amount.divide(
                        fromRate,
                        10,
                        RoundingMode.HALF_UP
                );

        return usdAmount
                .multiply(toRate)
                .setScale(2, RoundingMode.HALF_UP);
    }
}