package IPL;

import com.opencsv.bean.CsvBindByName;

public class BatsmanCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    private String playerName;

    @CsvBindByName(column = "Mat", required = true)
    private String matchesPlayed;

    @CsvBindByName(column = "Inns", required = true)
    private String inningsPlayed;

    @CsvBindByName(column = "NO", required = true)
    private String notOutInnings;

    @CsvBindByName(column = "Runs", required = true)
    private String totalRuns;

    @CsvBindByName(column = "HS", required = true)
    private String highestScore;

    @CsvBindByName(column = "Avg", required = true)
    private String battingAverage;

    @CsvBindByName(column = "BF", required = true)
    private String ballsFaced;

    @CsvBindByName(column = "SR", required = true)
    private String batingStrikeRate;

    @CsvBindByName(column = "100", required = true)
    private String centuries;

    @CsvBindByName(column = "50", required = true)
    private String halfCenturies;

    @CsvBindByName(column = "4s", required = true)
    private String fours;

    @CsvBindByName(column = "6s", required = true)
    private String sixes;

    public String getPlayerName() {
        return playerName;
    }

    public String getMatchesPlayed() {
        return matchesPlayed;
    }

    public String getInningsPlayed() {
        return inningsPlayed;
    }

    public String getNotOutInnings() {
        return notOutInnings;
    }

    public String getTotalRuns() {
        return totalRuns;
    }

    public String getHighestScore() {
        return highestScore;
    }

    public String getBattingAverage() {
        return battingAverage;
    }

    public String getBallsFaced() {
        return ballsFaced;
    }

    public String getBatingStrikeRate() {
        return batingStrikeRate;
    }

    public String getCenturies() {
        return centuries;
    }

    public String getHalfCenturies() {
        return halfCenturies;
    }

    public String getFours() {
        return fours;
    }

    public String getSixes() {
        return sixes;
    }
}
