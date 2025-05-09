package nba.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Stats implements Serializable {
    private Integer id;
    private Integer playerId;

    private Integer shoot;
    private Integer pass;
    private Integer dribble;
    private Integer rebound;
    private Integer block;
    private Integer steal;
    private Integer point3;

    @Builder
    public Stats(Integer playerId ,Integer shoot, Integer pass, Integer dribble, Integer rebound, Integer block, Integer steal, Integer point3) {
        this.id = id;
        this.playerId = playerId;
        this.shoot = shoot;
        this.pass = pass;
        this.dribble = dribble;
        this.rebound = rebound;
        this.block = block;
        this.steal = steal;
        this.point3 = point3;
    }
}
