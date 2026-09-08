package State;

import Entity.Ball;
import Entity.Match;
import Entity.Ball;
import Entity.Match;

public class CompletedState implements MatchState {

    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println(
                "ERROR: Cannot process a ball for a completed match."
        );
    }

    @Override
    public void startMatch(Match match) {
        System.out.println(
                "ERROR: Completed match cannot be started again."
        );
    }

    @Override
    public void endMatch(Match match) {
        System.out.println(
                "ERROR: Match is already completed."
        );
    }

    @Override
    public String getStateName() {
        return "COMPLETED";
    }

    @Override
    public void startNextInnings(Match match) {
        System.out.println(
                "ERROR: Cannot start another innings. The match is already completed."
        );
    }

}
