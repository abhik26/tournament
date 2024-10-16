package abhik26.tournament.util;

import java.util.ArrayList;
import java.util.List;

import abhik26.tournament.dto.MatchLevelDTO;
import abhik26.tournament.dto.PlayerDTO;
import abhik26.tournament.entity.MatchLevel;
import abhik26.tournament.entity.Player;

public class EntityToDto {

    public static PlayerDTO convertPlayer(Player player) {
        PlayerDTO playerDTO = null;

        if (player != null) {
            playerDTO = new PlayerDTO();
            playerDTO.setId(player.getId());
            playerDTO.setName(player.getName());
            playerDTO.setEmail(player.getEmail());
            playerDTO.setMobileNo(player.getMobileNo());
            playerDTO.setRoomNo(player.getRoomNo());
        }

        return playerDTO;
    }

    public static List<PlayerDTO> convertPlayers(List<Player> players) {
        List<PlayerDTO> playersToReturn = new ArrayList<PlayerDTO>();

        if (players != null && !players.isEmpty()) {
            for (Player player : players) {
                playersToReturn.add(EntityToDto.convertPlayer(player));
            }
        }

        return playersToReturn;
    }

    public static MatchLevelDTO convertMatchLevel(MatchLevel matchLevel) {
        MatchLevelDTO matchLevelDTO = null;

        if (matchLevel != null) {
            matchLevelDTO = new MatchLevelDTO();
            matchLevelDTO.setId(matchLevel.getId());
            matchLevelDTO.setName(matchLevel.getName());
            matchLevelDTO.setValue(matchLevel.getValue());
            matchLevelDTO.setMaxPlayers(matchLevel.getMaxPlayers());
        }

        return matchLevelDTO;
    }

    public static List<MatchLevelDTO> convertMatchLevels(List<MatchLevel> matchLevels) {
        List<MatchLevelDTO> matchLevelDTOs = new ArrayList<MatchLevelDTO>();

        if (matchLevels != null && !matchLevels.isEmpty()) {
            for (MatchLevel matchLevel : matchLevels) {
                matchLevelDTOs.add(EntityToDto.convertMatchLevel(matchLevel));
            }
        }

        return matchLevelDTOs;
    }
}
