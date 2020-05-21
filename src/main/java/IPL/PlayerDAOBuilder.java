package IPL;

import opencsv.CSVBuilderException;
import opencsv.CSVBuilderFactory;
import opencsv.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class PlayerDAOBuilder {

    Map<String, PlayerDAO> playerDataMap = new HashMap<>();

    public Map<String, PlayerDAO> loadPlayersData(String batsmanCSVFilePath, String bowlerCSVFilePath) throws PlayerAnalyserException {
        loadBatsmanData(batsmanCSVFilePath);
        loadBowlersData(bowlerCSVFilePath);
        return playerDataMap;
    }

    private void loadBatsmanData(String batsmanCSVFilePath) throws PlayerAnalyserException {
        Iterable<BatsmanCSV> csvIterable = getIterable(batsmanCSVFilePath, BatsmanCSV.class);
        StreamSupport.stream(csvIterable.spliterator(), false)
                    .parallel()
                    .forEach(batsmanCSV -> playerDataMap.put(batsmanCSV.getPlayerName(), new PlayerDAO(batsmanCSV)));
    }

    private void loadBowlersData(String bowlerCSVFilePath) throws PlayerAnalyserException {
        Iterable<BowlerCSV> csvIterable = getIterable(bowlerCSVFilePath, BowlerCSV.class);
        StreamSupport.stream(csvIterable.spliterator(), false)
                            .parallel()
                            .forEach(bowlerCSV -> {
                                PlayerDAO playerDAO = playerDataMap.get(bowlerCSV.getPlayerName());
                                if (playerDAO == null)
                                    playerDataMap.put(bowlerCSV.getPlayerName(), new PlayerDAO(bowlerCSV));
                                else
                                    playerDAO.setBowlingData(bowlerCSV);
                            });
    }

    private <T> Iterable<T> getIterable(String path, Class<T> csvClass) throws PlayerAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            ICSVBuilder csvBuilder = CSVBuilderFactory.getCsvBuilder();
            Iterator<T> csvIterator = csvBuilder.getCsvIterator(reader, csvClass);
            return () -> csvIterator;
        } catch (CSVBuilderException e) {
            throw new PlayerAnalyserException(e.getMessage(), String.valueOf(e.type));
        } catch (IOException e) {
            throw new PlayerAnalyserException(e.getMessage(),
                    PlayerAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

}
