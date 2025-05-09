package nba.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Player extends Person implements Serializable {
    private Integer id;
    private Integer height;
    private Integer wingSpan;
    private String position;
    private Boolean isFA;
    private Integer teamId;

    @Builder
    public Player(Integer teamId,String name, LocalDate birth, Long salary, Integer id, Integer height, Integer wingSpan, String position, Boolean isFA) {
        super(name, birth, salary);
        this.teamId = teamId;
        this.id = id;
        this.height = height;
        this.wingSpan = wingSpan;
        this.position = position;
        this.isFA = isFA != null ? isFA : false;
    }
}
