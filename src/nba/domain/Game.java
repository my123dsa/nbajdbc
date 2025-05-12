package nba.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Game implements Serializable {
    private Integer id;
    private Integer team1;
    private Integer team2;

    private Integer team1Score;
    private Integer team2Score;
    private Boolean result;

    @Builder
    public Game(Integer id,Integer team1, Integer team2, Integer team1Score,Integer team2Score, Boolean result) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.result = result;
    }
}
