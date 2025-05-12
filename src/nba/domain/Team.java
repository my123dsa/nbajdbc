package nba.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Team implements Serializable {
    private Integer id;
    private String name;
    private Long money;
    private Integer wins;
    private Integer totalGames;

    @Builder
    public Team(Integer id,String name, Long money, Integer wins, Integer totalGames) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.wins = wins;
        this.totalGames = totalGames;
    }
}
