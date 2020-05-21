package IPL;

public class PlayerDTO {
    private final String playerName;
    private final int matchesPlayed;
    private final int inningsPlayed;
    private final int notOutInnings;
    private final int totalRuns;
    private final int highestScore;
    private final double battingAverage;
    private final int ballsFaced;
    private final double strikeRate;
    private final int centuries;
    private final int halfCenturies;
    private final int fours;
    private final int sixes;

    public PlayerDTO(PlayerDAO playerDAO) {
        this.playerName = playerDAO.playerName;
        this.matchesPlayed = playerDAO.matchesPlayed;
        this.inningsPlayed = playerDAO.inningsPlayed;
        this.notOutInnings = playerDAO.notOutInnings;
        this.totalRuns = playerDAO.totalRuns;
        this.highestScore = playerDAO.highestScore;
        this.battingAverage = playerDAO.battingAverage;
        this.ballsFaced = playerDAO.ballsFaced;
        this.strikeRate = playerDAO.batingStrikeRate;
        this.centuries = playerDAO.centuries;
        this.halfCenturies = playerDAO.halfCenturies;
        this.fours = playerDAO.fours;
        this.sixes = playerDAO.sixes;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getInningsPlayed() {
        return inningsPlayed;
    }

    public int getNotOutInnings() {
        return notOutInnings;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public int getCenturies() {
        return centuries;
    }

    public int getHalfCenturies() {
        return halfCenturies;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

}
