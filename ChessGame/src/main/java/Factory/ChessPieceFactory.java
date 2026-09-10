package Factory;
import ChessGame.Cell;
import Pieces.*;

public interface ChessPieceFactory {

    King createKing(Cell cell);

    Queen createQueen(Cell cell);

    Rook createRook(Cell cell);

    Bishop createBishop(Cell cell);

    Knight createKnight(Cell cell);

    Pawn createPawn(Cell cell);
}