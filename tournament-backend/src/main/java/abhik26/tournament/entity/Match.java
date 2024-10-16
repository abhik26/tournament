package abhik26.tournament.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceException;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private MatchLevel matchLevel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Player player1;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Player player2;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    @Size(min = 1)
    Set<MatchSet> sets;

    @Column(nullable = false, updatable = false)
    private Integer player1FinalScore;

    @Column(nullable = false, updatable = false)
    private Integer player2FinalScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchLevel getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(MatchLevel matchLevel) {
        this.matchLevel = matchLevel;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Set<MatchSet> getSets() {
        return sets;
    }

    public void setSets(Set<MatchSet> sets) {
        this.sets = sets;
    }

    @PrePersist @PreUpdate
    private void pre() {
        if (player1.getId().equals(player2.getId())) {
            throw new PersistenceException("player1 and player2 cannot be same for a match.");
        }

        this.player1FinalScore = 0;
        this.player2FinalScore = 0;

        for (MatchSet set : sets) {
            if (set.getPlayer1Score() > set.getPlayer2Score()) {
                this.player1FinalScore++;
            } else if (set.getPlayer2Score() < set.getPlayer1Score()) {
                this.player2FinalScore++;
            }
        }
    }
}
