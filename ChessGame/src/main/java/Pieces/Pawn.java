package Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;
import Strategy.PawnMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, Cell position) {

        super(color, position, new PawnMoveStrategy());
    }

    @Override
    public String getSymbol() {
        return getColor() == Color.WHITE ? "P" : "p";
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {

        List<Cell> moves = new ArrayList<>();

        int direction = getColor() == Color.WHITE ? -1 : 1;

        int row = getPosition().getRow();
        int col = getPosition().getCol();

        int newRow = row + direction;

        if (board.isValidPosition(newRow, col)) {

            Cell cell = board.getCell(newRow, col);

            if (cell.isEmpty()) {
                moves.add(cell);
            }
        }

        int startRow = getColor() == Color.WHITE ? 6 : 1;

        if (row == startRow) {

            int twoSteps = row + 2 * direction;

            if (board.isValidPosition(twoSteps, col)) {

                Cell middle = board.getCell(row + direction, col);

                Cell destination = board.getCell(twoSteps, col);

                if (middle.isEmpty() && destination.isEmpty()) {
                    moves.add(destination);
                }
            }
        }

        for (int colOffset : new int[]{-1, 1}) {

            int captureCol = col + colOffset;

            if (board.isValidPosition(newRow, captureCol)) {

                Cell cell = board.getCell(newRow, captureCol);

                if (!cell.isEmpty() && cell.getPiece().getColor() != getColor()) {

                    moves.add(cell);
                }
            }
        }

        return moves;
    }
}