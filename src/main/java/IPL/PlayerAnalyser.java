package IPL;

import com.google.gson.Gson;

import java.util.*;

public class PlayerAnalyser {
    Map<String, PlayerDAO> playerDataMap;

    public int loadPlayersData(String csvFilePath) throws PlayerAnalyserException {
        this.playerDataMap = new PlayerDAOBuilder().loadPlayersData(csvFilePath);
        return this.playerDataMap.size();
    }

    public List<String> getPlayersWithBestAverage() throws PlayerAnalyserException {
        throwNoDataException();
        Comparator<PlayerDAO> comparator = Comparator.comparing(PlayerDAO::getBattingAverage);
        List<PlayerDTO> dtoList = buildPlayerDTO(sort(new ArrayList<>(playerDataMap.values()),comparator));
        List<String> jsonDTO = toJSONString(dtoList);
        return jsonDTO;
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
