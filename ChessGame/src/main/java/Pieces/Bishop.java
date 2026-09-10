package Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;
import Strategy.BishopMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color, Cell position) {

        super(color, position, new BishopMoveStrategy());
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.WHITE ? "B" : "b";
    }


    @Override
    public List<Cell> getPossibleMoves(Board board) {

        List<Cell> moves = new ArrayList<>();

        addDirection(board, moves, 1, 1);
        addDirection(board, moves, 1, -1);
        addDirection(board, moves, -1, 1);
        addDirection(board, moves, -1, -1);

        return moves;
    }

    private void addDirection(Board board, List<Cell> moves, int rowStep, int colStep) {

        int row = getPosition().getRow() + rowStep;

        int col = getPosition().getCol() + colStep;

        while (board.isValidPosition(row, col)) {

            Cell cell = board.getCell(row, col);

            if (cell.isEmpty()) {

                moves.add(cell);

            } else {

                if (cell.getPiece().getColor() != getColor()) {

                    moves.add(cell);
                }

                break;
            }

            row += rowStep;
            col += colStep;
        }
    }
}
