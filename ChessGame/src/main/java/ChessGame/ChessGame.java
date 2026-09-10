package ChessGame;

import Pieces.Piece;

import java.util.Stack;

public class ChessGame {

    private final Board board;

    private final Player[] players;

    private int currentPlayerIndex;

    private boolean gameOver;

    private GameStatus status;

    private final Stack<Move> moveHistory;

    public ChessGame(Player whitePlayer, Player blackPlayer) {

        if (whitePlayer.getColor() != Color.WHITE) {

            throw new IllegalArgumentException("First player must be WHITE");
        }

        if (blackPlayer.getColor() != Color.BLACK) {

            throw new IllegalArgumentException("Second player must be BLACK");
        }

        this.players = new Player[]{whitePlayer, blackPlayer};

        this.board = new Board();

        this.currentPlayerIndex = 0;

        this.gameOver = false;

        this.status = GameStatus.NOT_STARTED;

        this.moveHistory = new Stack<>();
    }

    public void start() {

        status = GameStatus.IN_PROGRESS;

        System.out.println("Chess game started!");

        board.printBoard();
    }

    public void makeMove(Move move) {

        if (gameOver) {

            throw new InvalidMoveException("Game is already over");
        }

        Player player = getCurrentPlayer();

        Piece piece = move.getFrom().getPiece();

        if (piece == null) {

            throw new InvalidMoveException("No piece at source");
        }

        if (piece.getColor() != player.getColor()) {

            throw new InvalidMoveException("You can only move your own piece");
        }

        if (!piece.isValidMove(board, move)) {

            throw new InvalidMoveException("Invalid piece movement");
        }

        if (!move.getTo().isEmpty() && move.getTo().getPiece().getColor() == piece.getColor()) {

            throw new InvalidMoveException("Cannot capture own piece");
        }

        if (wouldLeaveKingInCheck(move, player.getColor())) {

            throw new InvalidMoveException("Move leaves your king in check");
        }

        board.movePiece(move);

        moveHistory.push(move);

        updateGameStatus();

        if (!gameOver) {
            switchPlayer();
        }
    }

    private boolean wouldLeaveKingInCheck(
            Move move,
            Color color) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        Piece movingPiece = from.getPiece();

        Piece capturedPiece = to.getPiece();

        from.setPiece(null);
        to.setPiece(movingPiece);

        movingPiece.setPosition(to);

        boolean check = board.isCheck(color);

        // Undo simulation

        to.setPiece(capturedPiece);
        from.setPiece(movingPiece);

        movingPiece.setPosition(from);

        return check;
    }

    private void updateGameStatus() {

        Color opponent = getCurrentPlayer().getColor().opposite();

        if (board.isCheckmate(opponent)) {

            status = GameStatus.CHECKMATE;

            gameOver = true;

            System.out.println("CHECKMATE! " + getCurrentPlayer().getName() + " wins!");

        } else if (
                board.isStalemate(opponent)) {

            status = GameStatus.STALEMATE;

            gameOver = true;

            System.out.println("STALEMATE! Game is a draw.");

        } else if (
                board.isCheck(opponent)) {
                status = GameStatus.CHECK;

                System.out.println(opponent + " is in CHECK.");

            } else {

            status = GameStatus.IN_PROGRESS;
        }
    }

    private void switchPlayer() {

        currentPlayerIndex = 1 - currentPlayerIndex;
    }

    public Player getCurrentPlayer() {

        return players[currentPlayerIndex];
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Stack<Move> getMoveHistory() {
        return moveHistory;
    }
}