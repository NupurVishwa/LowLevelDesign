package ChessGame;

public class Move {

    private final Cell from;
    private final Cell to;

    public Move(Cell from, Cell to) {
        this.from = from;
        this.to = to;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}