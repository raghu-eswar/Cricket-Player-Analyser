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

    public Map<String, PlayerDAO> loadPlayersData(String playerCSVFilePath) throws PlayerAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(playerCSVFilePath))) {
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

        return playerDataMap;
    }

}
