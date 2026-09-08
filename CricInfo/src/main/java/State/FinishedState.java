package State;

import Entity.Ball;
import Entity.Match;

public class FinishedState implements MatchState {

    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println(
                "ERROR: Cannot process a ball for a finished match."
        );
    }

    @Override
    public void startMatch(Match match) {
        System.out.println(
                "ERROR: Cannot start a match that has already finished."
        );
    }

    @Override
    public void endMatch(Match match) {
        System.out.println("Match is already finished.");
    }

    @Override
    public String getStateName() {
        return "FINISHED";
    }

    @Override
    public void startNextInnings(Match match) {
        System.out.println(
                "ERROR: Cannot start another innings. The match has already finished."
        );
    }

}
