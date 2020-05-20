package IPL;

import java.util.*;

public class PlayerAnalyser {
    Map<String, PlayerDAO> playerDataMap;

    public int loadPlayersData(String csvFilePath) throws PlayerAnalyserException {
        this.playerDataMap = new PlayerDAOBuilder().loadPlayersData(csvFilePath);
        return this.playerDataMap.size();
    }

}
