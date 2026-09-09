package Strategy;
import Model.Split;
import Model.User;
import Exception.InvalidSplitException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(
            BigDecimal totalAmount,
            List<User> users,
            List<BigDecimal> splitValues) {

        if (splitValues == null ||
                splitValues.size() != users.size()) {

            throw new InvalidSplitException(
                    "Exact split values must match number of users"
            );
        }

        BigDecimal totalSplit = BigDecimal.ZERO;

        for (BigDecimal value : splitValues) {

            if (value == null ||
                    value.compareTo(BigDecimal.ZERO) < 0) {

                throw new InvalidSplitException(
                        "Split amount cannot be negative"
                );
            }

            totalSplit = totalSplit.add(value);
        }

        if (totalSplit.compareTo(totalAmount) != 0) {
            throw new InvalidSplitException(
                    "Exact split amounts must equal expense amount"
            );
        }

        List<Split> splits = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {

            splits.add(
                    new Split(
                            users.get(i),
                            splitValues.get(i)
                    )
            );
        }

        return splits;
    }
}