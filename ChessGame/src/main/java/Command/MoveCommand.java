package Command;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Move;
import Pieces.Piece;

public class MoveCommand implements Command {

    private final Board board;
    private final Move move;

    private Piece capturedPiece;

    public MoveCommand(Board board, Move move) {

        this.board = board;
        this.move = move;
    }

    @Override
    public void execute() {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        Piece movingPiece = from.getPiece();

        capturedPiece = to.getPiece();

        from.setPiece(null);

        to.setPiece(movingPiece);

        movingPiece.setPosition(to);
    }

    @Override
    public void undo() {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        Piece movingPiece = to.getPiece();

        to.setPiece(capturedPiece);

        from.setPiece(movingPiece);

        movingPiece.setPosition(from);
    }
}