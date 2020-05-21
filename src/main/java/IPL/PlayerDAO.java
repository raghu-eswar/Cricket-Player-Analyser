package IPL;

public class PlayerDAO {
    public String playerName;
    public int matchesPlayed;
    public int inningsPlayed;
    public int notOutInnings;
    public int totalRuns;
    public int highestScore;
    public double battingAverage;
    public int ballsFaced;
    public double batingStrikeRate;
    public int centuries;
    public int halfCenturies;
    public int fours;
    public int sixes;
    public double oversBowled;
    public int wicketsTaken;
    public double bowlingAverage;
    public int bestBowlingInning;
    public double bowlingStrikeRate;
    public double economy;
    public int fourWicketsInInnings;
    public int fiveWicketsInInnings;
    public double rating;


    public PlayerDAO() {  }

    public PlayerDAO(String playerName, String matchesPlayed, String inningsPlayed, String totalRuns) {
        this.playerName = playerName;
        this.matchesPlayed = getInteger(matchesPlayed);
        this.inningsPlayed = getInteger(inningsPlayed);
        this.totalRuns = getInteger(totalRuns);
    }

    public PlayerDAO(BatsmanCSV batsmanCSV) {
        this(batsmanCSV.getPlayerName(), batsmanCSV.getMatchesPlayed(), batsmanCSV.getInningsPlayed(), batsmanCSV.getTotalRuns());
        this.notOutInnings = getInteger(batsmanCSV.getNotOutInnings());
        this.highestScore = getInteger(batsmanCSV.getHighestScore());
        this.battingAverage = getDouble(batsmanCSV.getBattingAverage());
        this.ballsFaced = getInteger(batsmanCSV.getBallsFaced());
        this.batingStrikeRate = getDouble(batsmanCSV.getBatingStrikeRate());
        this.centuries = getInteger(batsmanCSV.getCenturies());
        this.halfCenturies = getInteger(batsmanCSV.getHalfCenturies());
        this.fours = getInteger(batsmanCSV.getFours());
        this.sixes = getInteger(batsmanCSV.getSixes());
    }

    public PlayerDAO(BowlerCSV bowlerCSV) {
        this(bowlerCSV.getPlayerName(), bowlerCSV.getMatchesPlayed(), bowlerCSV.getInningsPlayed(), bowlerCSV.getTotalRuns());
         setBowlingData(bowlerCSV);
    }

    public void setBowlingData(BowlerCSV bowlerCSV) {
        this.oversBowled = getDouble(bowlerCSV.getOversBowled());
        this.wicketsTaken = getInteger(bowlerCSV.getWicketsTaken());
        this.bowlingAverage = getDouble(bowlerCSV.getBowlingAverage());
        this.bestBowlingInning = getInteger(bowlerCSV.getBestBowlingInning());
        this.economy = getDouble(bowlerCSV.getEconomy());
        this.bowlingStrikeRate = getDouble(bowlerCSV.getBowlingStrikeRate());
        this.fourWicketsInInnings = getInteger(bowlerCSV.getFourWicketsInInnings());
        this.fiveWicketsInInnings = getInteger(bowlerCSV.getFiveWicketsInInnings());
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            string = getNumber(string);
            return (string.isEmpty())? 0 : Integer.parseInt(string);
        }
    }

    private double getDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            string = getNumber(string);
            return (string.isEmpty())? 0 : Double.parseDouble(string);
        }
    }

    private String getNumber(String string) {
        return string.replaceAll("[^\\d.]", "");
    }

    @Override
    public String toString() {
        return "PlayerDAO{" +
                "playerName='" + playerName + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", inningsPlayed=" + inningsPlayed +
                ", notOutInnings=" + notOutInnings +
                ", totalRuns=" + totalRuns +
                ", highestScore=" + highestScore +
                ", battingAverage=" + battingAverage +
                ", ballsFaced=" + ballsFaced +
                ", batingStrikeRate=" + batingStrikeRate +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", oversBowled=" + oversBowled +
                ", wicketsTaken=" + wicketsTaken +
                ", bowlingAverage=" + bowlingAverage +
                ", bestBowlingInning=" + bestBowlingInning +
                ", bowlingStrikeRate=" + bowlingStrikeRate +
                ", economy=" + economy +
                ", fourWicketsInInnings=" + fourWicketsInInnings +
                ", fiveWicketsInInnings=" + fiveWicketsInInnings +
                ", rating=" + rating +
                '}';
    }

}
