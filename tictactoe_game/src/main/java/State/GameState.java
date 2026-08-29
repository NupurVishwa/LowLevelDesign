package State;
import Enum.GameStatus;
import TicTacToe.Game;


/*
 * STATE PATTERN
 *
 * Instead of:
 *
 * if(win)
 * if(draw)
 * if(progress)
 *
 * We create different objects
 * representing each state.
 */

public interface GameState {

    void handleMove(Game game,
                    int row,
                    int col);

    GameStatus getStatus();
}