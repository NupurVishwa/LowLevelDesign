package TicTacToe;

import Models.Player;
import Observer.ConsoleObserver;
import Strategy.NInARowWinningStrategy;


public class TicTacToeSystem {

    public Game createGame(Player p1,
                           Player p2,
                           int size) {

        Game game =
                new Game(
                        p1,
                        p2,
                        size,
                        new NInARowWinningStrategy()
                );

        // Register observer

        game.addObserver(
                new ConsoleObserver()
        );

        return game;
    }
}