package State;

import Entities.Player;
import Enum.PlayerStatus;

public interface PlayerState {
    void play(Player player);
    void pause(Player player);
    void stop(Player player);
}