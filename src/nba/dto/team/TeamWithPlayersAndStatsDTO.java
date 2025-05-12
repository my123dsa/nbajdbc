package nba.dto.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nba.dto.player.PlayerWithStats;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamWithPlayersAndStatsDTO {
    private Integer id;
    private String name;
    private Long money;
    private Integer wins;
    private Integer totalGames;

    private List<PlayerWithStats> players;
}
