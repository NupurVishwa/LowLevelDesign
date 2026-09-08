package State;

import Entity.Ball;
import Entity.Match;

public interface MatchState {

    void processBall(Match match, Ball ball);

    void startMatch(Match match);

    void endMatch(Match match);

    String getStateName();

    void startNextInnings(Match match);
}