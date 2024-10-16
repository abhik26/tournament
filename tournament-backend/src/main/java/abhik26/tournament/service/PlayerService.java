package abhik26.tournament.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import abhik26.tournament.dto.PlayerDTO;
import abhik26.tournament.entity.Player;
import abhik26.tournament.repository.PlayerRepository;
import abhik26.tournament.util.EntityToDto;

@Service
@Transactional
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        List<PlayerDTO> playersToReturn = new ArrayList<PlayerDTO>();

        List<Player> players = this.playerRepository.findAll(Sort.by(Order.asc("name")));
        playersToReturn = EntityToDto.convertPlayers(players);

        return playersToReturn;
    }

    public PlayerDTO addPlayer(@Valid Player player) {
        Player playerAdded = playerRepository.save(player);
        PlayerDTO playerDTO = EntityToDto.convertPlayer(playerAdded);

        return playerDTO;
    }

    public boolean deletePlayer(Integer id) {
        boolean playerDeleted = false;

        this.playerRepository.deleteById(id);
        playerDeleted = true;

        return playerDeleted;
    }

    public int addPlayers(MultipartFile playerData) throws Exception {
        int playersAddedCount = 0;

        Map<String, Integer> titleIndexMap = new HashMap<String, Integer>();
        List<String> titles = new ArrayList<String>();
        titles.add("Name");
        titles.add("Email");
        titles.add("Mobile");
        titles.add("Room");

        try (Workbook workbook = new XSSFWorkbook(playerData.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Row titleRow = sheet.getRow(0);

            for (int cellIndex = 0; cellIndex < titleRow.getLastCellNum(); cellIndex++) {
                Cell cell = titleRow.getCell(cellIndex);
                String title = cell.getStringCellValue();                

                if (titles.indexOf(title) >= 0) {
                    titleIndexMap.put(title, cell.getColumnIndex());
                }
            }

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                Player player = new Player();

                for (Map.Entry<String, Integer> entry : titleIndexMap.entrySet()) {
                    String title = entry.getKey();
                    Integer columnIndex = entry.getValue();

                    String cellValue = new DataFormatter().formatCellValue(row.getCell(columnIndex));

                    if ("Name".equals(title)) {
                        player.setName(cellValue);
                    } else if ("Email".equals(title)) {
                        player.setEmail(cellValue);
                    } else if ("Mobile".equals(title)) {
                        player.setMobileNo(cellValue);
                    } else if ("Room".equals(title)) {
                        player.setRoomNo(cellValue);
                    }
                }

                playerRepository.saveAndFlush(player);
                playersAddedCount++;
            }
        } catch (IOException e) {
            throw e;
        }

        return playersAddedCount;
    }
}
