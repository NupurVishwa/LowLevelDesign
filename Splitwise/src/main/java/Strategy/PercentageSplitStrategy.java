package Strategy;
import Model.Split;
import Model.User;
import Exception.InvalidSplitException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(
            BigDecimal totalAmount,
            List<User> users,
            List<BigDecimal> percentages) {

        if (percentages == null ||
                percentages.size() != users.size()) {

            throw new InvalidSplitException(
                    "Percentage values must match users"
            );
        }

        BigDecimal totalPercentage = BigDecimal.ZERO;

        for (BigDecimal percentage : percentages) {

            if (percentage == null ||
                    percentage.compareTo(BigDecimal.ZERO) < 0 ||
                    percentage.compareTo(BigDecimal.valueOf(100)) > 0) {

                throw new InvalidSplitException(
                        "Percentage must be between 0 and 100"
                );
            }

            totalPercentage =
                    totalPercentage.add(percentage);
        }

        if (totalPercentage.compareTo(
                BigDecimal.valueOf(100)) != 0) {

            throw new InvalidSplitException(
                    "Percentages must add up to 100"
            );
        }

        List<Split> splits = new ArrayList<>();

        BigDecimal allocated = BigDecimal.ZERO;

        for (int i = 0; i < users.size(); i++) {

            BigDecimal amount;

            if (i == users.size() - 1) {

                amount = totalAmount.subtract(allocated);

            } else {

                amount = totalAmount
                        .multiply(percentages.get(i))
                        .divide(
                                BigDecimal.valueOf(100),
                                2,
                                RoundingMode.HALF_UP
                        );
            }

            splits.add(
                    new Split(
                            users.get(i),
                            amount
                    )
            );

            allocated = allocated.add(amount);
        }

        return splits;
    }
}