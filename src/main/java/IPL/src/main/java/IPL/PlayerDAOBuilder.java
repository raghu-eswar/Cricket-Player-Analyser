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
        try (Reader reader = Files.newBufferedReader(Paths.get(batsmanCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getCsvBuilder();
            Iterator<BatsmanCSV> csvIterator = csvBuilder.getCsvIterator(reader, BatsmanCSV.class);
            Iterable<BatsmanCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .parallel()
                    .forEach(batsmanCSV -> playerDataMap.put(batsmanCSV.getPlayerName(), new PlayerDAO(batsmanCSV)));
        } catch (IOException e) {
            throw new PlayerAnalyserException(e.getMessage(),
                    PlayerAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new PlayerAnalyserException(e.getMessage(), String.valueOf(e.type));
        }
        loadBowlersData(bowlerCSVFilePath);
        return playerDataMap;
    }

    public void loadBowlersData(String playerCSVFilePath) throws PlayerAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(playerCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.getCsvBuilder();
            Iterator<BowlerCSV> csvIterator = csvBuilder.getCsvIterator(reader, BowlerCSV.class);
            Iterable<BowlerCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .parallel()
                    .forEach(bowlerCSV -> {
                        PlayerDAO playerDAO = playerDataMap.get(bowlerCSV.getPlayerName());
                        if (playerDAO == null)
                            playerDataMap.put(bowlerCSV.getPlayerName(), new PlayerDAO(bowlerCSV));
                        else
                            playerDAO.setBowlingData(bowlerCSV);
                    });
        } catch (IOException e) {
            throw new PlayerAnalyserException(e.getMessage(),
                    PlayerAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new PlayerAnalyserException(e.getMessage(), String.valueOf(e.type));
        }
    }

}
