package Models;
import Enum.GameEventType;
public class GameEvent {

    private final GameEventType type;

    private final Player player;

    private final int row;
    private final int col;

    public GameEvent(GameEventType type,
                     Player player,
                     int row,
                     int col) {

        this.type = type;
        this.player = player;
        this.row = row;
        this.col = col;
    }

    public GameEventType getType() {
        return type;
    }

    public Player getPlayer() {
        return player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
