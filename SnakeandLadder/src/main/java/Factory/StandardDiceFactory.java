package Factory;

import Strategy.Dice;
import Strategy.StandardDice;

public class StandardDiceFactory extends DiceFactory {

    private final int numberOfDice;

    public StandardDiceFactory(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    @Override
    public Dice createDice() {

        return new StandardDice(numberOfDice);
    }
}