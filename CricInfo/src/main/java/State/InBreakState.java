package State;

import Entity.Ball;
import Entity.Match;
import Enum.MatchStatus;

public class InBreakState implements MatchState {
    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println(
                "ERROR: Cannot process a ball. The match is currently in a break."
        );
    }

    @Override
    public void startMatch(Match match) {
        System.out.println(
                "ERROR: Match is already in progress. Cannot start it again."
        );
    }

    @Override
    public void endMatch(Match match) {
        System.out.println("Ending the match...");

        match.setState(new FinishedState());
        match.setCurrentStatus(MatchStatus.FINISHED);
    }

    @Override
    public String getStateName() {
        return "IN_BREAK";
    }

    @Override
    public void startNextInnings(Match match) {
        System.out.println("Starting the next innings...");

        match.createNewInnings();

        match.setState(new LiveState());
        match.setCurrentStatus(MatchStatus.LIVE);
    }
}
