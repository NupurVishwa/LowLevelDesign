package State;

import Entity.Ball;
import Entity.Match;
import Enum.MatchStatus;

public class ScheduledState implements MatchState {

    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println(
                "ERROR: Cannot process a ball. The match has not started yet."
        );
    }

    @Override
    public void startMatch(Match match) {
        System.out.println("Match has started.");

        match.setState(new LiveState());
        match.setCurrentStatus(MatchStatus.LIVE);
    }

    @Override
    public void endMatch(Match match) {
        System.out.println(
                "ERROR: Cannot end the match before it starts."
        );
    }

    @Override
    public String getStateName() {
        return "SCHEDULED";
    }

    @Override
    public void startNextInnings(Match match) {
        System.out.println(
                "ERROR: Cannot start the next innings before the match starts."
        );
    }
}
