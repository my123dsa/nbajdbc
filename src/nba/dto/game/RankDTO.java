package nba.dto.game;

import lombok.*;
import nba.domain.Team;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankDTO {
    private int rank;
    private int wins;
    private int totalGames;
    private String name;

    public static RankDTO of(Team team, int rank) {
        return RankDTO.builder()
                .rank(rank)
                .wins(team.getWins())
                .name(team.getName())
                .totalGames(team.getTotalGames())
                .build();
    }
}
