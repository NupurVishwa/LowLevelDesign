package Factory;
import Exception.InvalidSplitException;
import Strategy.EqualSplitStrategy;
import Strategy.ExactSplitStrategy;
import Strategy.PercentageSplitStrategy;
import Strategy.SplitStrategy;
import Enum.SplitType;

public class SplitStrategyFactory {

    private SplitStrategyFactory() {
    }

    public static SplitStrategy getStrategy(
            SplitType splitType) {

        if (splitType == null) {
            throw new InvalidSplitException(
                    "Split type cannot be null"
            );
        }

        switch (splitType) {

            case EQUAL:
                return new EqualSplitStrategy();

            case EXACT:
                return new ExactSplitStrategy();

            case PERCENTAGE:
                return new PercentageSplitStrategy();

            default:
                throw new InvalidSplitException(
                        "Unsupported split type"
                );
        }
    }
}