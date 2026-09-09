package Strategy;
import Model.Split;
import Model.User;
import Exception.InvalidSplitException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(
            BigDecimal totalAmount,
            List<User> users,
            List<BigDecimal> splitValues) {

        if (users == null || users.isEmpty()) {
            throw new InvalidSplitException(
                    "At least one user is required"
            );
        }

        BigDecimal share = totalAmount.divide(
                BigDecimal.valueOf(users.size()),
                2,
                RoundingMode.HALF_UP
        );

        List<Split> splits = new ArrayList<>();

        BigDecimal allocated = BigDecimal.ZERO;

        for (int i = 0; i < users.size(); i++) {

            BigDecimal amount = share;

            /*
             * Give rounding remainder to last user.
             */
            if (i == users.size() - 1) {
                amount = totalAmount.subtract(allocated);
            }

            splits.add(
                    new Split(users.get(i), amount)
            );

            allocated = allocated.add(amount);
        }

        return splits;
    }


}