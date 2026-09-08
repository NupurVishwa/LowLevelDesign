package State;

public interface MatchFormatStrategy {

    int getNumberOfOversPerInnings();

    int getNumberOfPlayersPerTeam();

    int getTotalInnings();

    int getOversPerInnings();

    String getFormatName();
}