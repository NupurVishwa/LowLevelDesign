public class RegularFineStrategy implements FineStrategy {

    private static final double FINE_PER_DAY = 10.0;

    @Override
    public double calculateFine(long overdueDays) {
        return overdueDays * FINE_PER_DAY;
    }
}