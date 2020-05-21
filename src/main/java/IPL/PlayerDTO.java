package IPL;

public class PlayerDTO {
    public final String playerName;
    public final int matchesPlayed;
    public final int inningsPlayed;
    public final int notOutInnings;
    public final int totalRuns;
    public final int highestScore;
    public final double battingAverage;
    public final int ballsFaced;
    public final double batingStrikeRate;
    public final int centuries;
    public final int halfCenturies;
    public final int fours;
    public final int sixes;
    public final double oversBowled;
    public final int wicketsTaken;
    public final double bowlingAverage;
    public final int bestBowlingInning;
    public final double bowlingStrikeRate;
    public final double economy;
    public final int fourWicketsInInnings;
    public final int fiveWicketsInInnings;

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

}
