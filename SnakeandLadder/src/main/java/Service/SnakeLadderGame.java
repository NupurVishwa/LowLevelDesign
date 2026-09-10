package Service;

import Entity.Board;
import Entity.GameState;
import Entity.Player;
import Strategy.Dice;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeLadderGame {

    private final Board board;

    /*
     * Strategy Pattern.
     *
     * Game depends on Dice interface,
     * not StandardDice directly.
     */
    private final Dice dice;

    private final Queue<Player> players;

    private GameState state;

    private Player winner;

    public SnakeLadderGame(Board board, Dice dice) {

        this.board = board;
        this.dice = dice;

        this.players = new LinkedList<>();

        this.state = GameState.NOT_STARTED;
    }

    public void addPlayer(Player player) {

        if (state != GameState.NOT_STARTED) {

            throw new IllegalStateException("Cannot add player after game has started");
        }

        if (player == null) {

            throw new IllegalArgumentException("Player cannot be null");
        }

        players.offer(player);
    }

    public void startGame() {

        if (players.size() < 2) {

            throw new IllegalStateException("At least two players are required");
        }

        state = GameState.IN_PROGRESS;

        System.out.println();
        System.out.println("======================================");
        System.out.println("       SNAKE AND LADDER GAME");
        System.out.println("======================================");

        System.out.println("Board Size: " + board.getSize());

        System.out.println("Players: " + players.size());

        System.out.println();
    }

    public void playTurn() {

        if (state != GameState.IN_PROGRESS) {

            throw new IllegalStateException("Game is not in progress");
        }

        Player player = players.poll();

        int oldPosition = player.getPosition();

        /*
         * Strategy Pattern is used here.
         *
         * We don't care whether dice is:
         *
         * StandardDice
         * FixedDice
         * FutureDice
         *
         * We simply call roll().
         */
        int diceValue = dice.roll();

        System.out.println("--------------------------------------");

        System.out.println("Turn: " + player.getName());

        System.out.println("Dice rolled: " + diceValue);

        int newPosition = oldPosition + diceValue;

        /*
         * Exact position rule.
         */
        if (newPosition > board.getSize()) {

            System.out.println("Move exceeds board size.");

            System.out.println(player.getName() + " stays at " + oldPosition);

            players.offer(player);

            return;
        }

        System.out.println("Move: " + oldPosition + " -> " + newPosition);

        /*
         * Check snake or ladder.
         */
        int destination = board.getDestination(newPosition);

        if (destination < newPosition) {

            System.out.println("Snake! " + newPosition + " -> " + destination);

        } else if (destination > newPosition) {

            System.out.println("Ladder! " + newPosition+ " -> " + destination);
        }

        player.setPosition(destination);

        System.out.println(player.getName() + " is now at " + destination);

        /*
         * Winner check.
         */
        if (destination == board.getSize()) {

            winner = player;

            state = GameState.FINISHED;

            System.out.println();
            System.out.println("======================================");

            System.out.println("WINNER: " + player.getName());

            System.out.println("======================================");

            return;
        }

        /*
         * Next player's turn.
         */
        players.offer(player);
    }

    public GameState getState() {
        return state;
    }

    public Player getWinner() {
        return winner;
    }
}