package ChessGame;

public class ChessGameDemo {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("            CHESS GAME DEMO");
        System.out.println("========================================");

        // Create players
        Player whitePlayer = new Player("Nupur", Color.WHITE);

        Player blackPlayer = new Player("Computer", Color.BLACK);

        // Create game
        ChessGame game = new ChessGame(whitePlayer, blackPlayer);

        // Start game
        game.start();

        System.out.println();
        System.out.println("========================================");
        System.out.println("             GAME STARTED");
        System.out.println("========================================");

        /*
         * Move 1
         * White: e2 -> e4
         */
        playMove(game, "e2", "e4");

        /*
         * Move 1
         * Black: e7 -> e5
         */
        playMove(game, "e7", "e5");

        /*
         * Move 2
         * White: g1 -> f3
         */
        playMove(game, "g1", "f3");

        /*
         * Move 2
         * Black: b8 -> c6
         */
        playMove(game, "b8", "c6");

        /*
         * Move 3
         * White: f1 -> c4
         */
        playMove(game, "f1", "c4");

        /*
         * Move 3
         * Black: g8 -> f6
         */
        playMove(game, "g8", "f6");

        /*
         * Move 4
         * White: d2 -> d3
         */
        playMove(game, "d2", "d3");

        /*
         * Move 4
         * Black: f8 -> c5
         */
        playMove(game, "f8", "c5");

        /*
         * Move 5
         * White: c1 -> g5
         */
        playMove(game, "c1", "g5");

        /*
         * Move 5
         * Black: h7 -> h6
         */
        playMove(game, "h7", "h6");

        /*
         * Try an invalid move
         */
        System.out.println();
        System.out.println("========================================");
        System.out.println("        TESTING INVALID MOVE");
        System.out.println("========================================");

        playMove(game, "e4", "e5");

        /*
         * Final information
         */
        System.out.println();
        System.out.println("========================================");
        System.out.println("           GAME INFORMATION");
        System.out.println("========================================");

        System.out.println("Current Player : " + game.getCurrentPlayer().getName());

        System.out.println("Current Color  : " + game.getCurrentPlayer().getColor());

        System.out.println("Game Status    : " + game.getStatus());

        System.out.println("Total Moves    : " + game.getMoveHistory().size());

        System.out.println();
        System.out.println("========================================");
        System.out.println("             FINAL BOARD");
        System.out.println("========================================");

        game.getBoard().printBoard();
    }


    private static void playMove(ChessGame game, String from, String to) {

        System.out.println();
        System.out.println("----------------------------------------");

        System.out.println("Turn : " + game.getCurrentPlayer().getName() + " (" + game.getCurrentPlayer().getColor() + ")");

        System.out.println("Move : " + from + " -> " + to);

        try {
            Cell fromCell = getCell(game, from);

            Cell toCell = getCell(game, to);

            Move move = new Move(fromCell, toCell);

            game.makeMove(move);

            System.out.println("Move successful!");

            System.out.println("Status : " + game.getStatus());

            System.out.println();
            game.getBoard().printBoard();

        } catch (InvalidMoveException e) {

            System.out.println("INVALID MOVE: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("ERROR: " + e.getMessage());
        }
    }


    private static Cell getCell(ChessGame game, String position) {

        /*
         * Convert chess notation:
         *
         * e2
         * e -> column
         * 2 -> row
         */

        char column = position.charAt(0);

        int row = Character.getNumericValue(position.charAt(1));

        /*
         * Board is expected to provide
         * access to cells.
         */
        return game.getBoard().getCell(row, column);
    }
}

//       a   b   c   d   e   f   g   h
//     +---+---+---+---+---+---+---+---+
//  8  |   |   |   |   |   |   |   |   |  8
//  7  |   |   |   |   |   |   |   |   |  7
//  6  |   |   |   |   |   |   |   |   |  6
//  5  |   |   |   |   |   |   |   |   |  5
//  4  |   |   |   |   |   |   |   |   |  4
//  3  |   |   |   |   |   |   |   |   |  3
//  2  |   |   |   |   |   |   |   |   |  2
//  1  |   |   |   |   |   |   |   |   |  1
//     +---+---+---+---+---+---+---+---+
//       a   b   c   d   e   f   g   h

/*a1	column a, row 1
b1	column b, row 1
e2	column e, row 2
e4	column e, row 4
g1	column g, row 1
f3	column f, row 3
h8*/