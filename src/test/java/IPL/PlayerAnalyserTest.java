package IPL;

import org.junit.Assert;
import org.junit.Test;

public class PlayerAnalyserTest {

    private static final String IPL_BATSMAN_CSV_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BOWLERS_CSV_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_WRONG_CSV_DATA_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenBatsmanCSVFileData_shouldReturn_CorrectNumberOfRecords() throws PlayerAnalyserException {
        int noOfRecordsLoaded = new PlayerAnalyser().loadPlayersData(IPL_BATSMAN_CSV_DATA_FILE_PATH);
        Assert.assertEquals(100, noOfRecordsLoaded);
    }

    @Test
    public void givenBatsmanCSVFileData_WithWrongFile_ShouldThrowException() {
        try {
            new PlayerAnalyser().loadPlayersData(IPL_WRONG_CSV_DATA_FILE_PATH);
        } catch (PlayerAnalyserException e) {
            Assert.assertEquals(PlayerAnalyserException.ExceptionType.CSV_FILE_PROBLEM,e.type);
        }
    }

}
