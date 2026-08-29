package Models;
import Enum.Symbol;
/*
 * Board owns all cells.
 *
 * Responsibility:
 * - create board
 * - validate coordinates
 * - place symbol
 * - print board
 *
 * Notice:
 * Board DOES NOT decide winner.
 * That responsibility belongs to Strategy.
 */

import Exceptions.InvalidMoveException;

public class Board {

    private final int size;
    private final Cell[][] grid;

    public Board(int size) {

        this.size = size;
        grid = new Cell[size][size];

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isValid(int row, int col) {

        return row >= 0 &&
                row < size &&
                col >= 0 &&
                col < size;
    }

    public boolean isCellEmpty(int row, int col) {

        validate(row, col);

        return grid[row][col].isEmpty();
    }

    public void placeSymbol(int row,
                            int col,
                            Symbol symbol) {

        validate(row, col);

        if (!grid[row][col].isEmpty()) {

            throw new InvalidMoveException(
                    "Cell already occupied!"
            );
        }

        grid[row][col].setSymbol(symbol);
    }

    public Symbol getSymbol(int row, int col) {

        validate(row, col);

        return grid[row][col].getSymbol();
    }

    private void validate(int row, int col) {

        if (!isValid(row, col)) {

            throw new InvalidMoveException(
                    "Invalid board position"
            );
        }
    }

    public boolean isFull() {

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                if (grid[i][j].isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {

        System.out.println();

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                Symbol symbol = grid[i][j].getSymbol();

                if (symbol == null)
                    System.out.print(" - ");
                else
                    System.out.print(" " + symbol + " ");

                if (j != size - 1)
                    System.out.print("|");
            }

            System.out.println();

            if (i != size - 1) {

                for (int j = 0; j < size; j++) {

                    System.out.print("---");

                    if (j != size - 1)
                        System.out.print("+");
                }

                System.out.println();
            }
        }

        System.out.println();
    }
}