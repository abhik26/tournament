package abhik26.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import abhik26.tournament.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
