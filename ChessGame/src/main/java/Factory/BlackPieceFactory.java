package Factory;

import ChessGame.Cell;
import Pieces.*;
import ChessGame.Color;
public class BlackPieceFactory implements ChessPieceFactory {

    @Override
    public King createKing(Cell cell) {
        return new King(Color.BLACK, cell);
    }

    @Override
    public Queen createQueen(Cell cell) {
        return new Queen(Color.BLACK, cell);
    }

    @Override
    public Rook createRook(Cell cell) {
        return new Rook(Color.BLACK, cell);
    }

    @Override
    public Bishop createBishop(Cell cell) {
        return new Bishop(Color.BLACK, cell);
    }

    @Override
    public Knight createKnight(Cell cell) {
        return new Knight(Color.BLACK, cell);
    }

    @Override
    public Pawn createPawn(Cell cell) {
        return new Pawn(Color.BLACK, cell);
    }
}