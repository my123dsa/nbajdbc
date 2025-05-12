package nba.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerWithStats {

    private Integer id;
    private String name;
    private LocalDate birth;
    private Long salary;
    private Integer height;
    private Integer wingSpan;
    private String position;
    private Boolean isFA;

    private Integer shoot;
    private Integer pass;
    private Integer dribble;
    private Integer rebound;
    private Integer block;
    private Integer steal;
    private Integer point3;
}
