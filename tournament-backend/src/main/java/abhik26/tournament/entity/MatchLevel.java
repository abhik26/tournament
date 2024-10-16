package abhik26.tournament.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"value", "maxPlayers"}))
public class MatchLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotBlank
    private String name;

    @Column(nullable = false, updatable = false)
    @NotNull
    @Min(value = 1)
    private Integer value;

    @Column(nullable = false, updatable = false)
    @NotNull
    private Integer maxPlayers;

    @OneToMany(mappedBy = "matchLevel")
    private List<Match> matches;

    @OneToMany(mappedBy = "matchLevel")
    private List<Player> players;

    public MatchLevel() {
        
    }

    public MatchLevel(@NotNull @NotBlank String name, @NotNull @Min(1) Integer value) {
        this.name = name;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @PrePersist
    private void PrePersist() {
        this.maxPlayers = (int) Math.pow(2, this.value);
    }
}
