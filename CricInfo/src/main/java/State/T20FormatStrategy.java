package State;
public class T20FormatStrategy implements MatchFormatStrategy {

    @Override
    public int getNumberOfOversPerInnings() {
        return 20;
    }

    @Override
    public int getNumberOfPlayersPerTeam() {
        return 11;
    }

    @Override
    public int getTotalInnings() {
        return 0;
    }

    @Override
    public int getOversPerInnings() {
        return 0;
    }

    @Override
    public String getFormatName() {
        return "T20";
    }
}