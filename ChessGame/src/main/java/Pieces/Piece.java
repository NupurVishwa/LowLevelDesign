package Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;
import ChessGame.Move;
import Strategy.MoveStrategy;

import java.util.List;

public abstract class Piece {

    private final Color color;

    private Cell position;

    private final MoveStrategy moveStrategy;

    protected Piece(Color color, Cell position, MoveStrategy moveStrategy) {

        this.color = color;
        this.position = position;
        this.moveStrategy = moveStrategy;
    }

    public Color getColor() {
        return color;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public boolean isValidMove(Board board, Move move) {

        return moveStrategy.isValidMove(board, move, this);
    }

    public abstract String getSymbol();

    public abstract List<Cell> getPossibleMoves(Board board);
}