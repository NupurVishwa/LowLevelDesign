package Strategy;
import Enum.Symbol;
import Models.Board;

public class NInARowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, int row, int col, Symbol symbol) {

            int size = board.getSize();

            boolean rowWin = true;
            boolean colWin = true;

            // Check entire row

            for (int j = 0; j < size; j++) {

                if (board.getSymbol(row, j) != symbol) {

                    rowWin = false;
                    break;
                }
            }

            // Check entire column

            for (int i = 0; i < size; i++) {

                if (board.getSymbol(i, col) != symbol) {

                    colWin = false;
                    break;
                }
            }

            boolean mainDiagonal = true;
            boolean antiDiagonal = true;

            // Check main diagonal only if
            // current cell belongs to it

            if (row == col) {

                for (int i = 0; i < size; i++) {

                    if (board.getSymbol(i, i) != symbol) {

                        mainDiagonal = false;
                        break;
                    }
                }
            } else {

                mainDiagonal = false;
            }

            // Check anti diagonal

            if (row + col == size - 1) {

                for (int i = 0; i < size; i++) {

                    if (board.getSymbol(i,
                            size - 1 - i) != symbol) {

                        antiDiagonal = false;
                        break;
                    }
                }
            } else {

                antiDiagonal = false;
            }

            return rowWin ||
                    colWin ||
                    mainDiagonal ||
                    antiDiagonal;
            }

    }

