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
        this.playerName = playerDAO.getPlayerName();
        this.matchesPlayed = playerDAO.getMatchesPlayed();
        this.inningsPlayed = playerDAO.getInningsPlayed();
        this.notOutInnings = playerDAO.getNotOutInnings();
        this.totalRuns = playerDAO.getTotalRuns();
        this.highestScore = playerDAO.getHighestScore();
        this.battingAverage = playerDAO.getBattingAverage();
        this.ballsFaced = playerDAO.getBallsFaced();
        this.strikeRate = playerDAO.getStrikeRate();
        this.centuries = playerDAO.getCenturies();
        this.halfCenturies = playerDAO.getHalfCenturies();
        this.fours = playerDAO.getFours();
        this.sixes = playerDAO.getSixes();
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
