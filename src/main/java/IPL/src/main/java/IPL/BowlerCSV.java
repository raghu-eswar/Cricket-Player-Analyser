package IPL;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    private String playerName;

    @CsvBindByName(column = "Mat", required = true)
    private String matchesPlayed;

    @CsvBindByName(column = "Inns", required = true)
    private String inningsPlayed;

    @CsvBindByName(column = "Ov", required = true)
    private String oversBowled;

    @CsvBindByName(column = "Runs", required = true)
    private String totalRuns;

    @CsvBindByName(column = "Wkts", required = true)
    private String wicketsTaken;

    @CsvBindByName(column = "Avg", required = true)
    private String bowlingAverage;

    @CsvBindByName(column = "BBI", required = true)
    private String bestBowlingInning;

    @CsvBindByName(column = "SR", required = true)
    private String strikeRate;

    @CsvBindByName(column = "Econ", required = true)
    private String economy;

    @CsvBindByName(column = "4w", required = true)
    private String fourWicketsInInnings;

    @CsvBindByName(column = "5w", required = true)
    private String fiveWicketsInInnings;

    public String getPlayerName() {
        return playerName;
    }

    public String getMatchesPlayed() {
        return matchesPlayed;
    }

    public String getInningsPlayed() {
        return inningsPlayed;
    }

    public String getOversBowled() {
        return oversBowled;
    }

    public String getTotalRuns() {
        return totalRuns;
    }

    public String getWicketsTaken() {
        return wicketsTaken;
    }

    public String getBowlingAverage() {
        return bowlingAverage;
    }

    public String getBestBowlingInning() {
        return bestBowlingInning;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public String getEconomy() {
        return economy;
    }

    public String getFourWicketsInInnings() {
        return fourWicketsInInnings;
    }

    public String getFiveWicketsInInnings() {
        return fiveWicketsInInnings;
    }
}
