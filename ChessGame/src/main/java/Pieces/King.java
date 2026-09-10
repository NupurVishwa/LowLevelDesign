package Pieces;
import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;
import Strategy.KingMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Cell position) {

        super(color, position, new KingMoveStrategy());
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.WHITE ? "K" : "k";
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {

        List<Cell> moves = new ArrayList<>();

        int row = getPosition().getRow();
        int col = getPosition().getCol();

        for (int dr = -1; dr <= 1; dr++) {

            for (int dc = -1; dc <= 1; dc++) {

                if (dr == 0 && dc == 0) {
                    continue;
                }

                int newRow = row + dr;
                int newCol = col + dc;

                if (board.isValidPosition(newRow, newCol)) {

                    Cell cell = board.getCell(newRow, newCol);

                    if (cell.isEmpty() || cell.getPiece().getColor() != getColor()) {

                        moves.add(cell);
                    }
                }
            }
        }

        return moves;
    }
}