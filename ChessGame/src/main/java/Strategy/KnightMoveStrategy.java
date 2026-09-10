package Strategy;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Move;
import Pieces.Piece;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move, Piece piece) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        int rowDiff = Math.abs(to.getRow() - from.getRow());

        int colDiff = Math.abs(to.getCol() - from.getCol());

        return
                (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}