package ChessGame;
import Command.MoveCommand;
import Factory.BlackPieceFactory;
import Factory.ChessPieceFactory;
import Factory.WhitePieceFactory;
import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int SIZE = 8;

    private final Cell[][] grid;

    public Board() {

        grid = new Cell[SIZE][SIZE];

        initializeCells();
        initializePieces();
    }

    private void initializeCells() {

        for (int row = 0; row < SIZE; row++) {

            for (int col = 0; col < SIZE; col++) {

                grid[row][col] =
                        new Cell(row, col);
            }
        }
    }

    private void initializePieces() {

        ChessPieceFactory whiteFactory = new WhitePieceFactory();

        ChessPieceFactory blackFactory = new BlackPieceFactory();

        // WHITE BACK ROW

        placePiece(whiteFactory.createRook(getCell(7, 0)));

        placePiece(whiteFactory.createKnight(getCell(7, 1)));

        placePiece(whiteFactory.createBishop(getCell(7, 2)));

        placePiece(whiteFactory.createQueen(getCell(7, 3)));

        placePiece(whiteFactory.createKing(getCell(7, 4)));

        placePiece(whiteFactory.createBishop(getCell(7, 5)));

        placePiece(whiteFactory.createKnight(getCell(7, 6)));

        placePiece(whiteFactory.createRook(getCell(7, 7)));

        for (int col = 0; col < SIZE; col++) {

            placePiece(whiteFactory.createPawn(getCell(6, col)));
        }

        // BLACK BACK ROW

        placePiece(blackFactory.createRook(getCell(0, 0)));

        placePiece(blackFactory.createKnight(getCell(0, 1)));

        placePiece(blackFactory.createBishop(getCell(0, 2)));

        placePiece(blackFactory.createQueen(getCell(0, 3)));

        placePiece(blackFactory.createKing(getCell(0, 4)));

        placePiece(blackFactory.createBishop(getCell(0, 5)));

        placePiece(blackFactory.createKnight(getCell(0, 6)));

        placePiece(blackFactory.createRook(getCell(0, 7)));

        for (int col = 0; col < SIZE; col++) {

            placePiece(blackFactory.createPawn(getCell(1, col)));
        }
    }

    private void placePiece(Piece piece) {

        piece.getPosition().setPiece(piece);
    }

    public Cell getCell(int row, int col) {

        if (!isValidPosition(row, col)) {

            throw new IllegalArgumentException(
                    "Invalid position"
            );
        }

        return grid[row][col];
    }

    public boolean isValidPosition(int row, int col) {

        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public boolean isCellOccupied(int row, int col) {

        return !getCell(row, col).isEmpty();
    }

    public void movePiece(Move move) {

        MoveCommand command = new MoveCommand(this, move);

        command.execute();
    }

    public List<Piece> getPieces(Color color) {

        List<Piece> pieces = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {

            for (int col = 0; col < SIZE; col++) {

                Piece piece = grid[row][col].getPiece();

                if (piece != null && piece.getColor() == color) {

                    pieces.add(piece);
                }
            }
        }

        return pieces;
    }

    public King findKing(Color color) {

        for (Piece piece : getPieces(color)) {

            if (piece instanceof King) {
                return (King) piece;
            }
        }

        return null;
    }

    public boolean isCheck(Color color) {

        King king = findKing(color);

        if (king == null) {
            return false;
        }

        Cell kingCell = king.getPosition();

        for (Piece opponent : getPieces(color.opposite())) {

            Move attack = new Move(opponent.getPosition(), kingCell);

            if (opponent.isValidMove(this, attack)) {

                return true;
            }
        }

        return false;
    }

    public boolean hasAnyLegalMove(
            Color color) {

        for (Piece piece : getPieces(color)) {

            for (Cell destination : piece.getPossibleMoves(this)) {

                Move move = new Move(piece.getPosition(), destination);

                if (piece.isValidMove(this, move)) {

                    if (!wouldLeaveKingInCheck(move, color)) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean wouldLeaveKingInCheck(Move move, Color color) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        Piece movingPiece = from.getPiece();

        Piece capturedPiece = to.getPiece();

        from.setPiece(null);
        to.setPiece(movingPiece);

        movingPiece.setPosition(to);

        boolean result = isCheck(color);

        // Undo temporary move

        to.setPiece(capturedPiece);
        from.setPiece(movingPiece);

        movingPiece.setPosition(from);

        return result;
    }

    public boolean isCheckmate(Color color) {

        return isCheck(color) && !hasAnyLegalMove(color);
    }

    public boolean isStalemate(Color color) {

        return !isCheck(color) && !hasAnyLegalMove(color);
    }

    public void printBoard() {

        System.out.println();

        System.out.println("    0 1 2 3 4 5 6 7");

        for (int row = 0; row < SIZE; row++) {

            System.out.print(row + "   ");

            for (int col = 0; col < SIZE; col++) {

                Piece piece = grid[row][col].getPiece();

                if (piece == null) {

                    System.out.print(". ");

                } else {

                    System.out.print(piece.getSymbol() + " ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }
}