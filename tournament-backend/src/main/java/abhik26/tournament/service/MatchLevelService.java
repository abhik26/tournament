package abhik26.tournament.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import abhik26.tournament.dto.MatchLevelDTO;
import abhik26.tournament.entity.MatchLevel;
import abhik26.tournament.exception.TournamentException;
import abhik26.tournament.repository.MatchLevelRepository;
import abhik26.tournament.util.EntityToDto;

@Service
@Transactional
public class MatchLevelService {

    MatchLevelRepository matchLevelRepository;

    public MatchLevelService(MatchLevelRepository matchLevelRepository) {
        this.matchLevelRepository = matchLevelRepository;
    }

    public List<MatchLevelDTO> getMatchLevels() {
        List<MatchLevelDTO> matchLevelDTOs = new ArrayList<MatchLevelDTO>();

        List<MatchLevel> matchLevels = this.matchLevelRepository.findAll(Sort.by(Order.asc("value")));
        matchLevelDTOs = EntityToDto.convertMatchLevels(matchLevels);

        return matchLevelDTOs;
    }

    public MatchLevelDTO addMatchLevel(@Valid MatchLevel matchLevel) {
        MatchLevel mathLevelAdded = matchLevelRepository.save(matchLevel);
        MatchLevelDTO matchLevelDTO = EntityToDto.convertMatchLevel(mathLevelAdded);

        return matchLevelDTO;
    }

    public MatchLevelDTO updateName(Integer id, String name) {
        MatchLevelDTO matchLevelDTO = null;

        Optional<MatchLevel> matchLevelOptional = matchLevelRepository.findById(id);

        if (matchLevelOptional.isPresent()) {
            MatchLevel matchLevel = matchLevelOptional.get();
            matchLevel.setName(name);
            matchLevelDTO = EntityToDto.convertMatchLevel(matchLevel);
        } else {
            throw new TournamentException("Match level not found for id: " + id, HttpStatus.NOT_FOUND);
        }

        return matchLevelDTO;
    }
}
