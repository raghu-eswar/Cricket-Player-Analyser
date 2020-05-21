package IPL;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlayerAnalyserTest {
    private static final String IPL_BATSMAN_CSV_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BOWLERS_CSV_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_WRONG_CSV_DATA_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_correctNumberOfRecords() {
        try {
            int noOfRecordsLoaded = new PlayerAnalyser().loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH, IPL_BOWLERS_CSV_DATA_FILE_PATH);
            Assert.assertEquals(150, noOfRecordsLoaded);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBatsmanCSVFileData_WithWrongFile_shouldThrowException() {
        try {
            new PlayerAnalyser().loadPlayersData(IPL_WRONG_CSV_DATA_FILE_PATH, IPL_BOWLERS_CSV_DATA_FILE_PATH);
        } catch (PlayerAnalyserException e) {
            Assert.assertEquals(PlayerAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByBattingAverageOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH, IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.BATTING_AVERAGE);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("MS Dhoni",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Mayank Markande", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

    @Test
    public void withLoadingData_getPlayersWithBestAverage_shouldThrowException() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        try {
            analyser.getPlayersWithBest(PlayerAnalyser.Options.BATTING_AVERAGE);
        } catch (PlayerAnalyserException e) {
            Assert.assertEquals(PlayerAnalyserException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByStrikeRateOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH, IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.BATING_STRIKE_RATE);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("Ishant Sharma",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Mayank Markande", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByBoundariesOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH, IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.FOURS, PlayerAnalyser.Options.SIXES);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("Andre Russell",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Mayank Markande", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByStrikeRateAndBoundariesOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH,IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.BATING_STRIKE_RATE,PlayerAnalyser.Options.FOURS, PlayerAnalyser.Options.SIXES);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("Andre Russell",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Mayank Markande", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByStrikeRateAndBatingAverageOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH,IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.BATING_STRIKE_RATE,PlayerAnalyser.Options.BATTING_AVERAGE);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("MS Dhoni",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Mayank Markande", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByRunsAndBatingAverageOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH,IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.RUNS, PlayerAnalyser.Options.BATTING_AVERAGE);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("David Warner",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Hanuma Vihari", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_sortedListByBowlingAverageOfPlayers() {
        PlayerAnalyser analyser = new PlayerAnalyser();
        List<String> playerList = null;
        try {
            analyser.loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH, IPL_BOWLERS_CSV_DATA_FILE_PATH);
            playerList = analyser.getPlayersWithBest(PlayerAnalyser.Options.BOWLING_AVERAGE);
        } catch (PlayerAnalyserException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Assert.assertEquals(150, playerList.size());
        Assert.assertEquals("Anukul Roy",g.fromJson(playerList.get(0), PlayerDTO.class).playerName);
        Assert.assertEquals("Shreyas Iyer", g.fromJson(playerList.get(playerList.size()-1), PlayerDTO.class).playerName);
    }

}
