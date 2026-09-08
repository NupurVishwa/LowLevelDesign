package Entity;

import Enum.MatchStatus;
import Observer.MatchObserver;
import State.MatchFormatStrategy;
import State.MatchState;
import State.ScheduledState;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private final String id;
    private final Team team1;
    private final Team team2;

    private final MatchFormatStrategy formatStrategy;

    private final List<Innings> innings;

    private MatchState currentState;

    private MatchStatus currentStatus;

    private final List<MatchObserver> observers = new ArrayList<>();

    private Team winner;

    private String resultMessage;

    public Match(
            String id,
            Team team1,
            Team team2,
            MatchFormatStrategy formatStrategy
    ) {

        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.formatStrategy = formatStrategy;

        this.innings = new ArrayList<>();

        // First innings starts with team1 batting
        this.innings.add(new Innings(team1, team2));

        // Match starts in Scheduled state
        this.currentState = new ScheduledState();

        this.resultMessage = "";
    }

    // ==============================
    // STATE PATTERN
    // ==============================

    public void processBall(Ball ball) {
        currentState.processBall(this, ball);
    }

    public void startNextInnings() {
        currentState.startNextInnings(this);
    }

    public void setState(MatchState state) {
        this.currentState = state;
    }

    public MatchState getCurrentState() {
        return currentState;
    }

    public void setCurrentStatus(MatchStatus status) {
        this.currentStatus = status;
    }

    public MatchStatus getCurrentStatus() {
        return currentStatus;
    }

    // ==============================
    // MATCH RESULT
    // ==============================

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Team getWinner() {
        return winner;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    // ==============================
    // INNINGS
    // ==============================

    public void createNewInnings() {

        if (innings.size() >= formatStrategy.getTotalInnings()) {

            System.out.println(
                    "Cannot create a new innings, match has already reached its limit."
            );

            return;
        }

        Innings nextInnings =
                new Innings(team2, team1);

        innings.add(nextInnings);
    }

    public Innings getCurrentInnings() {
        return innings.get(innings.size() - 1);
    }

    public List<Innings> getInnings() {
        return innings;
    }

    // ==============================
    // OBSERVER PATTERN
    // ==============================

    public void addObserver(MatchObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MatchObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Ball ball) {

        for (MatchObserver observer : observers) {
            observer.update(this, ball);
        }
    }

    // ==============================
    // GETTERS
    // ==============================

    public String getId() {
        return id;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public MatchFormatStrategy getFormatStrategy() {
        return formatStrategy;
    }
}