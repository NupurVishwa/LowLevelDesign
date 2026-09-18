package Command;

import Entities.Player;

public class PlayCommand implements Command {
    private final Player player;

    public PlayCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.clickPlay();
    }
}