package IPL;

import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PlayerAnalyser {

    public enum Options {
        BATTING_AVERAGE, STRIKE_RATE, FOURS, SIXES;
    }

    private enum PropertyNames {
        BATTING_AVERAGE("battingAverage"), STRIKE_RATE("strikeRate"),FOURS("fours"), SIXES("sixes");
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

    public List<String> getPlayersWithBest(Options ... options) throws PlayerAnalyserException {
        throwNoDataException();
        String [] fieldNames = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            fieldNames[i] = PropertyNames.valueOf(options[i].name()).fieldName;
        }
        return getPlayersWithBest(fieldNames);
    }

    private List<String> getPlayersWithBest(String ... fieldNames) {
        setPlayersRating(fieldNames);
        Comparator<PlayerDAO> comparator = Comparator.comparing(PlayerDAO::getRating);
        List<PlayerDTO> dtoList = buildPlayerDTO(sort(new ArrayList<>(playerDataMap.values()),comparator));
        return toJSONString(dtoList);
    }

    private double getPlayerRating(PlayerDAO playerDAO, String fieldName, double finalMaxValue) {
        try {
            return ((Double.parseDouble(PropertyUtils.getProperty(playerDAO, fieldName).toString()))/ finalMaxValue)*100;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setPlayersRating(String ... fieldNames) {
        for (PlayerDAO player:playerDataMap.values()) {
            double maxValue = 0;
            double rating = 0;
            try {
                for (String fieldName : fieldNames) {
                    maxValue = Double.parseDouble(PropertyUtils.getProperty(getPlayerWithMaximumValue(fieldName), fieldName).toString());
                    rating = rating + getPlayerRating(player, fieldName, maxValue);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            double finalRating = rating/fieldNames.length;
            player.setRating(finalRating);
        }
    }

    private PlayerDAO getPlayerWithMaximumValue(String fieldName) {
        return playerDataMap.values()
                .stream()
                .reduce(new PlayerDAO(), (p1, p2) -> {
                    try {
                        double p1Value = Double.parseDouble(PropertyUtils.getProperty(p1, fieldName).toString());
                        double p2Value = Double.parseDouble(PropertyUtils.getProperty(p2, fieldName).toString());
                        return (p1Value>p2Value)? p1 : p2;
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
