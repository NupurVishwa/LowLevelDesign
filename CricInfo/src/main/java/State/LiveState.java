package State;

import Entity.Ball;
import Entity.Innings;
import Entity.Match;
import Enum.MatchStatus;

public class LiveState implements MatchState {

    @Override
    public void processBall(Match match, Ball ball) {

        System.out.println("Processing ball...");

        // Get the current innings
        Innings currentInnings = match.getCurrentInnings();

        // Add the ball and update score, wickets and player statistics
        currentInnings.addBall(ball);

        // Notify observers
        match.notifyObservers(ball);
    }

    @Override
    public void startMatch(Match match) {
        System.out.println("Match is already live.");
    }

    @Override
    public void endMatch(Match match) {
        System.out.println("Match has ended.");

        match.setState(new FinishedState());
        match.setCurrentStatus(MatchStatus.FINISHED);
    }

    @Override
    public void startNextInnings(Match match) {
        System.out.println(
                "ERROR: Current innings is still live. "
                        + "Start the next innings only after the current innings ends."
        );
    }

    @Override
    public String getStateName() {
        return "LIVE";
    }

}
