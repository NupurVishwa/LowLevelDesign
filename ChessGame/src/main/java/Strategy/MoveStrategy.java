package Strategy;

import ChessGame.Board;
import ChessGame.Move;
import Pieces.Piece;

public interface MoveStrategy {

    boolean isValidMove(Board board, Move move, Piece piece);
}