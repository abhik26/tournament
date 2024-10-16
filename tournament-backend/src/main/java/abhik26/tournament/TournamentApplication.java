package abhik26.tournament;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import abhik26.tournament.dto.MatchLevelDTO;
import abhik26.tournament.entity.MatchLevel;
import abhik26.tournament.service.MatchLevelService;

@SpringBootApplication
public class TournamentApplication implements ApplicationRunner {

	MatchLevelService matchLevelService;

	public TournamentApplication(MatchLevelService matchLevelService) {
		this.matchLevelService = matchLevelService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TournamentApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<MatchLevelDTO> matchLevels = matchLevelService.getMatchLevels();

		if (matchLevels == null || matchLevels.isEmpty()) {
			MatchLevel matchLevel1 = new MatchLevel("Final", 1);
			MatchLevel matchLevel2 = new MatchLevel("Semi Final", 2);
			MatchLevel matchLevel3 = new MatchLevel("Quarter Final", 3);

			matchLevelService.addMatchLevel(matchLevel1);
			matchLevelService.addMatchLevel(matchLevel2);
			matchLevelService.addMatchLevel(matchLevel3);
		}
	}

}
