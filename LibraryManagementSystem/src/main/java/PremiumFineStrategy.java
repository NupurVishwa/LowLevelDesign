public class PremiumFineStrategy implements FineStrategy {

    private static final double FINE_PER_DAY = 2.0;


    @Override
    public double calculateFine(long overdueDays) {

        return overdueDays * FINE_PER_DAY;
    }
}