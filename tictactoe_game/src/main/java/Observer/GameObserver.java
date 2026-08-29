package Observer;

import Models.GameEvent;

public interface GameObserver {

    void onGameEvent(GameEvent event);
}