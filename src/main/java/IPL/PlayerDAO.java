package IPL;

public class PlayerDAO {
    private String playerName;
    private int matchesPlayed;
    private int inningsPlayed;
    private int notOutInnings;
    private int totalRuns;
    private int highestScore;
    private double battingAverage;
    private int ballsFaced;
    private double strikeRate;
    private int centuries;
    private int halfCenturies;
    private int fours;
    private int sixes;

    public PlayerDAO(BatsmanCSV batsmanCSV) {
        this.playerName = batsmanCSV.getPlayerName();
        this.matchesPlayed = getInteger(batsmanCSV.getMatchesPlayed());
        this.inningsPlayed = getInteger(batsmanCSV.getInningsPlayed());
        this.notOutInnings = getInteger(batsmanCSV.getNotOutInnings());
        this.totalRuns = getInteger(batsmanCSV.getTotalRuns());
        this.highestScore = getInteger(batsmanCSV.getHighestScore());
        this.battingAverage = getDouble(batsmanCSV.getBattingAverage());
        this.ballsFaced = getInteger(batsmanCSV.getBallsFaced());
        this.strikeRate = getDouble(batsmanCSV.getStrikeRate());
        this.centuries = getInteger(batsmanCSV.getCenturies());
        this.halfCenturies = getInteger(batsmanCSV.getHalfCenturies());
        this.fours = getInteger(batsmanCSV.getFours());
        this.sixes = getInteger(batsmanCSV.getSixes());
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getInningsPlayed() {
        return inningsPlayed;
    }

    public void setInningsPlayed(int inningsPlayed) {
        this.inningsPlayed = inningsPlayed;
    }

    public int getNotOutInnings() {
        return notOutInnings;
    }

    public void setNotOutInnings(int notOutInnings) {
        this.notOutInnings = notOutInnings;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getCenturies() {
        return centuries;
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public int getHalfCenturies() {
        return halfCenturies;
    }

    public void setHalfCenturies(int halfCenturies) {
        this.halfCenturies = halfCenturies;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
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
                ", strikeRate=" + strikeRate +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }

}
