package Factory;
import ChessGame.Color;
import ChessGame.Cell;
import Pieces.*;


public class WhitePieceFactory
        implements ChessPieceFactory {

    @Override
    public King createKing(Cell cell) {
        return new King(Color.WHITE, cell);
    }

    @Override
    public Queen createQueen(Cell cell) {
        return new Queen(Color.WHITE, cell);
    }

    @Override
    public Rook createRook(Cell cell) {
        return new Rook(Color.WHITE, cell);
    }

    @Override
    public Bishop createBishop(Cell cell) {
        return new Bishop(Color.WHITE, cell);
    }

    @Override
    public Knight createKnight(Cell cell) {
        return new Knight(Color.WHITE, cell);
    }

    @Override
    public Pawn createPawn(Cell cell) {
        return new Pawn(Color.WHITE, cell);
    }
}