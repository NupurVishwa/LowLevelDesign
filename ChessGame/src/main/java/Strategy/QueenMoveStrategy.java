package Strategy;

import ChessGame.Board;
import ChessGame.Move;
import Pieces.Piece;

public class QueenMoveStrategy implements MoveStrategy {

    private final RookMoveStrategy rookStrategy = new RookMoveStrategy();

    private final BishopMoveStrategy bishopStrategy = new BishopMoveStrategy();

    @Override
    public boolean isValidMove(Board board, Move move, Piece piece) {

        return rookStrategy.isValidMove(board, move, piece) || bishopStrategy.isValidMove(board, move, piece);
    }
}