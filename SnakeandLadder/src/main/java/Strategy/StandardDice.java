package Strategy;

import java.util.Random;

public class StandardDice implements Dice {

    private final int numberOfDice;
    private final Random random;

    public StandardDice(int numberOfDice) {

        if (numberOfDice <= 0) {
            throw new IllegalArgumentException("Number of dice must be greater than zero");
        }

        this.numberOfDice = numberOfDice;
        this.random = new Random();
    }

    @Override
    public int roll() {

        int total = 0;

        for (int i = 0; i < numberOfDice; i++) {

            total += random.nextInt(6) + 1;
        }

        return total;
    }
}