package nba.service;

import lombok.RequiredArgsConstructor;
import nba.domain.Team;
import nba.dto.game.RankDTO;
import nba.repository.GameRepository;
import nba.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GameService {
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;

    public List<RankDTO> getRank() {

        List<Team> teams=  teamRepository.findAllWithRankAndName();

        int rank = 1;
        int prevScore = -1;  // 이전 팀의 점수
        int rankForTie = 1;  // 동점 순위를 위한 변수
        List<RankDTO> rankDTOs = new ArrayList<>();

        for (Team team : teams) {
            int score = team.getWins();

            // 동점일 경우
            if (score != prevScore) {
                // 동점이 아니면 순위 갱신
                rankForTie = rank;
            }
            RankDTO rankDTO = RankDTO.of(team, rankForTie);
            rankDTOs.add(rankDTO);

            prevScore = score;  // 현재 점수를 이전 점수로 설정
            rank++;  // 순위 증가
        }

        return rankDTOs;
    }
}
