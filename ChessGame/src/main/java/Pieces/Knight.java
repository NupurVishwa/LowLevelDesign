package Pieces;
import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;
import Strategy.KnightMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Cell position) {

        super(color, position, new KnightMoveStrategy());
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.WHITE ? "N" : "n";
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {

        List<Cell> moves = new ArrayList<>();

        int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

        int row = getPosition().getRow();
        int col = getPosition().getCol();

        for (int[] direction : directions) {

            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (board.isValidPosition(newRow, newCol)) {

                Cell cell = board.getCell(newRow, newCol);

                if (cell.isEmpty() || cell.getPiece().getColor() != getColor()) {

                    moves.add(cell);
                }
            }
        }

        return moves;
    }
}