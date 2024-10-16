package abhik26.tournament.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import abhik26.tournament.dto.MatchLevelDTO;
import abhik26.tournament.entity.MatchLevel;
import abhik26.tournament.service.MatchLevelService;

@RestController
@RequestMapping("/match-levels")
public class MatchLevelController {

    MatchLevelService matchLevelService;

    public MatchLevelController(MatchLevelService matchLevelService) {
        this.matchLevelService = matchLevelService;
    }

    @GetMapping
    public List<MatchLevelDTO> getMatchLevels() {
        return this.matchLevelService.getMatchLevels();
    }

    @PostMapping
    public MatchLevelDTO addMatchLevel(@Valid @RequestBody MatchLevel matchLevel) {
        MatchLevelDTO matchLevelAdded = matchLevelService.addMatchLevel(matchLevel);
        return matchLevelAdded;
    }
    
    @PatchMapping("{id}/{name}")
    public MatchLevelDTO updateMatchLevelName(@PathVariable Integer id, @PathVariable String name) {
        MatchLevelDTO matchLevelDTO = null;

        matchLevelDTO = matchLevelService.updateName(id, name);

        return matchLevelDTO;
    }
}
