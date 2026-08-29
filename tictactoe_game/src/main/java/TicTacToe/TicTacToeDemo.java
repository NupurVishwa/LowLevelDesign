package TicTacToe;

import Enum.Symbol;
import Enum.GameStatus;
import Exceptions.InvalidMoveException;
import Models.Player;

import java.util.Scanner;

/*
 * RUNNABLE CLASS
 *
 * Start program from here.
 */

public class TicTacToeDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player p1 =
                new Player("Alice", Symbol.X);

        Player p2 =
                new Player("Bob", Symbol.O);

        TicTacToeSystem system =
                new TicTacToeSystem();

        Game game =
                system.createGame(p1, p2, 3);

        System.out.println("===== TIC TAC TOE =====");

        game.getBoard().printBoard();

        while (game.getStatus()
                == GameStatus.IN_PROGRESS) {

            Player current =
                    game.getCurrentPlayer();

            System.out.println(
                    current.getName()
                            + "'s Turn ("
                            + current.getSymbol()
                            + ")"
            );

            System.out.print("Enter row: ");

            int row = scanner.nextInt();

            System.out.print("Enter col: ");

            int col = scanner.nextInt();

            try {

                game.makeMove(row, col);

                game.getBoard().printBoard();

            } catch (InvalidMoveException ex) {

                System.out.println(
                        "Invalid Move : "
                                + ex.getMessage()
                );
            }
        }

        scanner.close();
    }
}