package State;
import Enum.GameStatus;

/*
 * Once someone wins,
 * no further move is accepted.
 */

import Exceptions.GameOverException;
import TicTacToe.Game;


public class WonState
        implements GameState {

    @Override
    public void handleMove(Game game,
                           int row,
                           int col) {

        throw new GameOverException(
                "Game already finished!"
        );
    }

    @Override
    public GameStatus getStatus() {

        return GameStatus.WIN;
    }
}