package nba.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Owner {
    private Integer id;
    private Integer teamId;
    private String userType;
    private String email;
    private String password;

    @Builder
    public Owner(Integer id, Integer teamId, String userType, String email, String password) {
        this.id = id;
        this.teamId = teamId;
        this.userType = userType;
        this.email = email;
        this.password = password;
    }
}
