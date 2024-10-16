package abhik26.tournament.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import abhik26.tournament.dto.PlayerDTO;
import abhik26.tournament.entity.Player;
import abhik26.tournament.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> getPlayers() {
        return this.playerService.getAllPlayers();
    }

    @PostMapping
    public PlayerDTO addPlayer(@RequestBody @Valid Player player) {
        PlayerDTO playerAdded = playerService.addPlayer(player);
        return playerAdded;
    }

    @DeleteMapping("/{id}")
    public boolean deletePlayer(@PathVariable Integer id) {
        boolean playerDeleted = this.playerService.deletePlayer(id);
        return playerDeleted;
    }

    @PostMapping("/batch")
    public ResponseEntity<Object> addPlayersInBatch(@RequestParam("file") MultipartFile playerData) throws Exception {
        ResponseEntity<Object> response = null;

        if (playerData == null || playerData.isEmpty()) {
            response = new ResponseEntity<Object>("Player data file not provided", HttpStatus.BAD_REQUEST);
        } else {
            int playersAdded = playerService.addPlayers(playerData);
            response = new ResponseEntity<Object>("Players added: "
                    + playersAdded, HttpStatus.CREATED);
        }

        return response;
    }

}
