package TicTacToe;

import Enum.GameStatus;
import Enum.GameEventType;
import Models.Board;
import Models.GameEvent;
import Models.Player;
import Observer.GameObserver;
import State.DrawState;
import State.GameState;
import State.InProgressState;
import State.WonState;
import Strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

/*
 * GAME = CENTRAL CONTROLLER
 *
 * Responsibilities:
 *
 * 1. Manage players
 * 2. Maintain current turn
 * 3. Delegate move to State
 * 4. Delegate winner logic to Strategy
 * 5. Notify Observers
 *
 * Notice:
 *
 * Game does NOT contain winning algorithm.
 * Game does NOT print messages.
 *
 * This makes it highly extensible.
 */

public class Game {

    private final Board board;

    private final Player[] players;

    private int currentPlayerIndex;

    // STATE PATTERN
    private GameState currentState;

    // STRATEGY PATTERN
    private final WinningStrategy winningStrategy;

    // OBSERVER PATTERN
    private final List<GameObserver> observers;

    public Game(Player p1,
                Player p2,
                int boardSize,
                WinningStrategy winningStrategy) {

        this.board = new Board(boardSize);

        this.players = new Player[]{p1, p2};

        this.currentPlayerIndex = 0;

        this.currentState = new InProgressState();

        this.winningStrategy = winningStrategy;

        this.observers = new ArrayList<>();
    }

    /*
     * Public API
     *
     * Client always calls this method.
     *
     * State decides what should happen.
     */

    public void makeMove(int row, int col) {

        currentState.handleMove(this, row, col);
    }

    /*
     * Called ONLY by InProgressState.
     *
     * Actual game flow happens here.
     */

    public void processMove(int row, int col) {

        Player player = getCurrentPlayer();

        // Board validates occupied cells

        board.placeSymbol(
                row,
                col,
                player.getSymbol()
        );

        notifyObservers(
                new GameEvent(
                        GameEventType.MOVE_PLAYED,
                        player,
                        row,
                        col
                )
        );

        /*
         * STRATEGY PATTERN
         *
         * Game asks strategy:
         *
         * "Did this move win?"
         *
         * It does not know HOW strategy checks it.
         */

        if (winningStrategy.checkWinner(
                board,
                row,
                col,
                player.getSymbol())) {

            currentState = new WonState();

            notifyObservers(
                    new GameEvent(
                            GameEventType.GAME_WON,
                            player,
                            row,
                            col
                    )
            );

            return;
        }

        if (board.isFull()) {

            currentState = new DrawState();

            notifyObservers(
                    new GameEvent(
                            GameEventType.GAME_DRAW,
                            null,
                            row,
                            col
                    )
            );

            return;
        }

        switchPlayer();
    }

    /*
     * Observer registration
     */

    public void addObserver(GameObserver observer) {

        observers.add(observer);
    }

    private void notifyObservers(GameEvent event) {

        for (GameObserver observer : observers) {

            observer.onGameEvent(event);
        }
    }

    private void switchPlayer() {

        currentPlayerIndex =
                (currentPlayerIndex + 1) % 2;
    }

    public Player getCurrentPlayer() {

        return players[currentPlayerIndex];
    }

    public Board getBoard() {

        return board;
    }

    public GameStatus getStatus() {

        return currentState.getStatus();
    }
}


