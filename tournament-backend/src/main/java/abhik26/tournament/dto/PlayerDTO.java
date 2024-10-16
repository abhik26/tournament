package abhik26.tournament.dto;

import java.util.List;

public class PlayerDTO {
    private Integer id;
    private String name;
    private String email;
    private String mobileNo;
    private String roomNo;
    private List<MatchDTO> matches;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public List<MatchDTO> getMatches() {
        return matches;
    }
    
    public void setMatches(List<MatchDTO> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "PlayerDTO [Id=" + id + ", fullName=" + name + ", email=" + email + ", mobileNo=" + mobileNo
                + ", roomNo=" + roomNo + ", matches=" + matches + "]";
    }
}
