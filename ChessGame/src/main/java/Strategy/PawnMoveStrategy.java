package Strategy;
import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;
import ChessGame.Move;
import Pieces.Piece;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move, Piece piece) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        Color color = piece.getColor();

        int direction = color == Color.WHITE ? -1 : 1;

        int startRow = color == Color.WHITE ? 6 : 1;

        int rowDiff = to.getRow() - from.getRow();

        int colDiff = to.getCol() - from.getCol();

        // One step forward
        if (colDiff == 0 && rowDiff == direction && to.isEmpty()) {

            return true;
        }

        // Two steps from starting position
        if (colDiff == 0 && from.getRow() == startRow && rowDiff == 2 * direction && to.isEmpty()) {

            int middleRow = from.getRow() + direction;

            return board.getCell(middleRow, from.getCol()).isEmpty();
        }

        // Diagonal capture
        if (Math.abs(colDiff) == 1 && rowDiff == direction && !to.isEmpty() && to.getPiece().getColor() != color) {

            return true;
        }

        return false;
    }
}