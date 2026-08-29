package Strategy;
import Enum.Symbol;
import Models.Board;

public interface WinningStrategy {

    boolean checkWinner(Board board, int row, int col, Symbol symbol);
}