package Observer;

import Entity.Ball;
import Entity.Match;

public interface MatchObserver {
    void update(Match match, Ball lastBall);
}