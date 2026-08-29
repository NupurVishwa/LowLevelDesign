package Observer;

import Models.GameEvent;
import Enum.GameEventType;

public class ConsoleObserver implements  GameObserver{
    @Override
    public void onGameEvent(GameEvent event) {
        if (event.getType() == GameEventType.MOVE_PLAYED) {

            System.out.println(event.getPlayer().getName()
                            + " placed "
                            + event.getPlayer().getSymbol()
                            + " at ("
                            + event.getRow()
                            + ","
                            + event.getCol()
                            + ")"
            );
        }

        if (event.getType() == GameEventType.GAME_WON) {

            System.out.println(
                    "\nWINNER : " + event.getPlayer().getName()
            );
        }

        if (event.getType() == GameEventType.GAME_DRAW) {

            System.out.println(
                    "\nGAME DRAW!"
            );
        }
    }
}
