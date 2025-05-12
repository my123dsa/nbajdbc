package nba.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResultDTO {
    private Integer id;
    private String teamName1;
    private String teamName2;
    private Integer team1Score;
    private Integer team2Score;
    private Boolean result;
}
