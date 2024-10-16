package abhik26.tournament.dto;

public class MatchLevelDTO {
    private Integer id;
    private String name;
    private Integer value;
    private Integer maxPlayers;

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

    @Override
    public String toString() {
        return "MatchLevelDTO [id=" + id + ", name=" + name + ", value=" + value + ", maxPlayers=" + maxPlayers + "]";
    }
}
