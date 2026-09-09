package Strategy;
import Model.Split;
import Model.User;

import java.math.BigDecimal;
import java.util.List;

public interface SplitStrategy {

    List<Split> calculateSplits(
            BigDecimal totalAmount,
            List<User> users,
            List<BigDecimal> splitValues
    );
}