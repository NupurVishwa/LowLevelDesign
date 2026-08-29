package State;
import Enum.GameStatus;
import Exceptions.GameOverException;
import TicTacToe.Game;


/*
 * Same idea as WonState.
 *
 * Draw also prevents new moves.
 */

public class DrawState implements GameState {

    @Override
    public void handleMove(Game game, int row, int col) {

        throw new GameOverException(
                "Game ended in Draw!"
        );
    }

    @Override
    public GameStatus getStatus() {

        return GameStatus.DRAW;
    }
}
