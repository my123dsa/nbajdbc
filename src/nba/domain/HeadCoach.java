package nba.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HeadCoach extends Person implements Serializable {
    private Integer id;
    private Integer teamBonus;

    @Builder
    public HeadCoach(String name, LocalDate birth, Long salary, Integer id, Integer teamBonus) {
        super(name, birth, salary);
        this.id = id;
        this.teamBonus = teamBonus;
    }
}