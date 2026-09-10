package Factory;

import ChessGame.Cell;
import Pieces.*;
import ChessGame.Color;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Piece createPiece(String type, Color color, Cell cell) {

        return switch (type.toUpperCase()) {

            case "KING" -> new King(color, cell);

            case "QUEEN" -> new Queen(color, cell);

            case "ROOK" -> new Rook(color, cell);

            case "BISHOP" -> new Bishop(color, cell);

            case "KNIGHT" -> new Knight(color, cell);

            case "PAWN" -> new Pawn(color, cell);

            default -> throw new IllegalArgumentException("Unknown piece type: " + type);
        };
    }
}