package Strategy;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Move;
import Pieces.Piece;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move, Piece piece) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        boolean sameRow = from.getRow() == to.getRow();

        boolean sameColumn = from.getCol() == to.getCol();

        if (!sameRow && !sameColumn) {
            return false;
        }

        return isPathClear(board, from, to);
    }

    private boolean isPathClear(Board board, Cell from, Cell to) {

        int rowStep = Integer.compare(to.getRow(), from.getRow());

        int colStep = Integer.compare(to.getCol(), from.getCol());

        int row = from.getRow() + rowStep;
        int col = from.getCol() + colStep;

        while (row != to.getRow() || col != to.getCol()) {

            if (!board.getCell(row, col).isEmpty()) {
                return false;
            }

            row += rowStep;
            col += colStep;
        }

        return true;
    }
}
