package Entity;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final int size;

    /*
     * Stores:
     *
     * Snake:
     * 99 -> 10
     *
     * Ladder:
     * 4 -> 25
     */
    private final Map<Integer, Integer> jumps;

    public Board(int size) {

        if (size <= 1) {
            throw new IllegalArgumentException("Board size must be greater than 1");
        }

        this.size = size;
        this.jumps = new HashMap<>();
    }

    public void addSnake(Snake snake) {

        validatePosition(snake.getHead());
        validatePosition(snake.getTail());

        if (jumps.containsKey(snake.getHead())) {

            throw new IllegalArgumentException("Position already contains a snake or ladder");
        }

        jumps.put(snake.getHead(), snake.getTail());
    }

    public void addLadder(Ladder ladder) {

        validatePosition(ladder.getBottom());
        validatePosition(ladder.getTop());

        if (jumps.containsKey(ladder.getBottom())) {

            throw new IllegalArgumentException("Position already contains a snake or ladder");
        }

        jumps.put(ladder.getBottom(), ladder.getTop());
    }

    public int getDestination(int position) {

        return jumps.getOrDefault(position, position);
    }

    public int getSize() {
        return size;
    }

    private void validatePosition(int position) {

        if (position <= 0 || position > size) {

            throw new IllegalArgumentException(
                    "Position must be between 1 and " + size
            );
        }
    }
}