package Factory;

import Strategy.Dice;

public abstract class DiceFactory {

    /*
     * Factory Method
     */
    public abstract Dice createDice();
}