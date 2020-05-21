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
    private final double batingStrikeRate;
    private final int centuries;
    private final int halfCenturies;
    private final int fours;
    private final int sixes;
    private final double oversBowled;
    private final int wicketsTaken;
    private final double bowlingAverage;
    private final int bestBowlingInning;
    private final double bowlingStrikeRate;
    private final double economy;
    private final int fourWicketsInInnings;
    private final int fiveWicketsInInnings;

    public PlayerDTO(PlayerDAO playerDAO) {
        this.playerName = playerDAO.playerName;
        this.matchesPlayed = playerDAO.matchesPlayed;
        this.inningsPlayed = playerDAO.inningsPlayed;
        this.notOutInnings = playerDAO.notOutInnings;
        this.totalRuns = playerDAO.totalRuns;
        this.highestScore = playerDAO.highestScore;
        this.battingAverage = playerDAO.battingAverage;
        this.ballsFaced = playerDAO.ballsFaced;
        this.batingStrikeRate = playerDAO.batingStrikeRate;
        this.centuries = playerDAO.centuries;
        this.halfCenturies = playerDAO.halfCenturies;
        this.fours = playerDAO.fours;
        this.sixes = playerDAO.sixes;
        this.oversBowled = playerDAO.oversBowled;
        this.wicketsTaken = playerDAO.wicketsTaken;
        this.bowlingAverage = playerDAO.bowlingAverage;
        this.bestBowlingInning = playerDAO.bestBowlingInning;
        this.economy = playerDAO.economy;
        this.bowlingStrikeRate = playerDAO.bowlingStrikeRate;
        this.fourWicketsInInnings = playerDAO.fourWicketsInInnings;
        this.fiveWicketsInInnings = playerDAO.fiveWicketsInInnings;
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

    public double getBatingStrikeRate() {
        return batingStrikeRate;
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

    public double getOversBowled() {
        return oversBowled;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public double getBowlingAverage() {
        return bowlingAverage;
    }

    public int getBestBowlingInning() {
        return bestBowlingInning;
    }

    public double getBowlingStrikeRate() {
        return bowlingStrikeRate;
    }

    public double getEconomy() {
        return economy;
    }

    public int getFourWicketsInInnings() {
        return fourWicketsInInnings;
    }

    public int getFiveWicketsInInnings() {
        return fiveWicketsInInnings;
    }
}
