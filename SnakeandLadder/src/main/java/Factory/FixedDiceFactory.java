package Factory;
import Strategy.Dice;
import Strategy.FixedDice;

public class FixedDiceFactory extends DiceFactory {

    private final int value;

    public FixedDiceFactory(int value) {
        this.value = value;
    }

    @Override
    public Dice createDice() {

        return new FixedDice(value);
    }
}