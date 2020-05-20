package IPL;

import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PlayerAnalyser {

    public enum Options {
        BATTING_AVERAGE, STRIKE_RATE;
    }

    private enum PropertyNames {
        BATTING_AVERAGE("battingAverage"), STRIKE_RATE("strikeRate");
        private final String fieldName;
        PropertyNames(String fieldName) {
            this.fieldName = fieldName;
        }
    }

    Map<String, PlayerDAO> playerDataMap;

    public int loadPlayersData(String csvFilePath) throws PlayerAnalyserException {
        this.playerDataMap = new PlayerDAOBuilder().loadPlayersData(csvFilePath);
        return this.playerDataMap.size();
    }

    public List<String> getPlayersWithBest(Options options) throws PlayerAnalyserException {
        throwNoDataException();
        return getPlayersWithBest(PropertyNames.valueOf(options.name()).fieldName);
    }

    private List<String> getPlayersWithBest(String fieldName) {
        double maxValue = 0;
        try {
            maxValue = (double) PropertyUtils.getProperty(getPlayerWithMaximumValue(fieldName), fieldName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        double finalMaxValue = maxValue;
        playerDataMap.values().parallelStream().forEach(playerDAO -> setPlayerRating(playerDAO,fieldName,finalMaxValue));
        Comparator<PlayerDAO> comparator = Comparator.comparing(PlayerDAO::getRating);
        List<PlayerDTO> dtoList = buildPlayerDTO(sort(new ArrayList<>(playerDataMap.values()),comparator));
        return toJSONString(dtoList);
    }

    private void setPlayerRating(PlayerDAO playerDAO, String fieldName, double finalMaxValue) {
        try {
            double rating = ((double)PropertyUtils.getProperty(playerDAO, fieldName)/ finalMaxValue)*100;
            playerDAO.setRating(rating);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private PlayerDAO getPlayerWithMaximumValue(String fieldName) {
        return playerDataMap.values()
                .stream()
                .reduce(new PlayerDAO(), (p1, p2) -> {
                    try {
                        return ((double)PropertyUtils.getProperty(p1, fieldName)>(double)PropertyUtils.getProperty(p2, fieldName))? p1 : p2;
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return p1;
                });
    }

    private List<String> toJSONString(List<PlayerDTO> dtoList) {
        List<String> jsonList = new ArrayList<>();
        dtoList.forEach(playerDTO -> jsonList.add(new Gson().toJson(playerDTO)));
        return jsonList;
    }

    private List<PlayerDTO> buildPlayerDTO(List<PlayerDAO> playerDAOS) {

        List<PlayerDTO> playerDTOS = new LinkedList<>();
        for (PlayerDAO playerDAO : playerDAOS) {
            playerDTOS.add(new PlayerDTO(playerDAO));
        }
        return playerDTOS;
    }

    private void throwNoDataException() throws PlayerAnalyserException {
        if (playerDataMap == null || playerDataMap.size() == 0 )
            throw new PlayerAnalyserException("no players data loaded ",PlayerAnalyserException.ExceptionType.NO_DATA);
    }

    private List<PlayerDAO> sort(List<PlayerDAO> list, Comparator<PlayerDAO> comparator) {
        list.sort(Collections.reverseOrder(comparator));
        return list;
    }

}
