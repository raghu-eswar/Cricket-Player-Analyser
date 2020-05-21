package IPL;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;

public class PlayerAnalyser {

    public enum Options {
        BATTING_AVERAGE, BATING_STRIKE_RATE, FOURS, SIXES,RUNS,BOWLING_AVERAGE
    }

    private enum PropertyNames {
        BATTING_AVERAGE("battingAverage"), BATING_STRIKE_RATE("batingStrikeRate"),
        FOURS("fours"), SIXES("sixes"), RUNS("totalRuns"), BOWLING_AVERAGE("bowlingAverage");
        private final String fieldName;
        PropertyNames(String fieldName) {
            this.fieldName = fieldName;
        }
    }

    private enum NegativeProperties {
        bowlingAverage
    }

    Map<String, PlayerDAO> playerDataMap;

    public int loadPlayersData(String batsmanCSVFilePath, String bowlerCSVFilePath) throws PlayerAnalyserException {
        this.playerDataMap = new PlayerDAOBuilder().loadPlayersData(batsmanCSVFilePath, bowlerCSVFilePath);
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
        Comparator<PlayerDAO> comparator = Comparator.comparing(playerDAO -> playerDAO.rating);
        List<PlayerDTO> dtoList = buildPlayerDTO(sort(new ArrayList<>(playerDataMap.values()),comparator));
        return toJSONString(dtoList);
    }

    private double getPlayerRating(PlayerDAO playerDAO, String fieldName, double finalMaxValue) {
        double rating = 0;
        try {
            double propertyValue = (Double.parseDouble(playerDAO.getClass().getDeclaredField(fieldName).get(playerDAO).toString()));
            if (propertyValue == 0)
                return 0;
            rating =  (propertyValue / finalMaxValue) * 100;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (isNegativeProperty(fieldName))? 100-rating : rating ;
    }

    private boolean isNegativeProperty(String fieldName) {
        for(NegativeProperties property : NegativeProperties.values())
            if (property.name().equals(fieldName))
                return true;
        return false;
    }

    private void setPlayersRating(String ... fieldNames) {
        for (PlayerDAO player:playerDataMap.values()) {
            double maxValue;
            double rating = 0;
            try {
                for (String fieldName : fieldNames) {
                    PlayerDAO playerWithMaximumValue = getPlayerWithMaximumValue(fieldName);
                    maxValue = Double.parseDouble(playerWithMaximumValue.getClass().getDeclaredField(fieldName).get(playerWithMaximumValue).toString());
                    rating = rating + getPlayerRating(player, fieldName, maxValue);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            player.rating = rating/fieldNames.length;
        }
    }

    private PlayerDAO getPlayerWithMaximumValue(String fieldName) {
        return playerDataMap.values()
                .stream()
                .reduce(new PlayerDAO(), (p1, p2) -> {
                    try {
                        Field field1 = p1.getClass().getDeclaredField(fieldName);
                        Field field2 = p2.getClass().getDeclaredField(fieldName);
                        double p1Value = Double.parseDouble(field1.get(p1).toString());
                        double p2Value = Double.parseDouble(field2.get(p2).toString());
                        return (p1Value>p2Value)? p1 : p2;
                    } catch (IllegalAccessException | NoSuchFieldException e) {
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
