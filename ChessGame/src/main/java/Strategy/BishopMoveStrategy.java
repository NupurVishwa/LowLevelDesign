package Strategy;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Move;
import Pieces.Piece;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move, Piece piece) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        int rowDiff = Math.abs(to.getRow() - from.getRow());

        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (rowDiff != colDiff) {
            return false;
        }

        int rowStep = Integer.compare(to.getRow(), from.getRow());

        int colStep = Integer.compare(to.getCol(), from.getCol());

        int row = from.getRow() + rowStep;
        int col = from.getCol() + colStep;

        while (row != to.getRow()) {

            if (!board.getCell(row, col).isEmpty()) {
                return false;
            }

            row += rowStep;
            col += colStep;
        }

        return true;
    }
}