package State;
import Enum.GameStatus;
import TicTacToe.Game;


public class InProgressState implements GameState {

    @Override
    public void handleMove(Game game,
                           int row,
                           int col) {

        game.processMove(row, col);
    }

    @Override
    public GameStatus getStatus() {

        return GameStatus.IN_PROGRESS;
    }
}

