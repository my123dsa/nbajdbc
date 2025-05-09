package nba.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class owner {
    private Integer id;
    private String userType;
    private String email;
    private String password;

    @Builder
    public owner(Integer id, String userType, String email, String password) {
        this.id = id;
        this.userType = userType;
        this.email = email;
        this.password = password;
    }
}
