package nba.service;

import lombok.RequiredArgsConstructor;
import nba.controller.MainController;
import nba.domain.Game;
import nba.domain.Team;
import nba.dto.game.QuarterLog;
import nba.dto.game.RankDTO;
import nba.dto.player.PlayerWithStats;
import nba.dto.team.TeamWithPlayersAndStatsDTO;
import nba.repository.GameRepository;
import nba.repository.TeamRepository;

import java.util.*;

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

    public List<QuarterLog> doGame() {
        Set<Integer> set=new HashSet<>(List.of(1,2,3,4));

        Integer id= MainController.getOwnerState().getTeamId();
        Integer vs;
        do{
            vs = (int) (Math.random() * 4) +1;
        }while (Objects.equals(id, vs));
        List<QuarterLog> logs = new ArrayList<>();
        Game g1= simulate(id,vs,true,logs);
        set.remove(vs);
        set.remove(id);
        Game g2 = otherTeamDoGame(set,logs);
        List<Game> gameList = List.of(g1,g2);
        gameRepository.saveAll(gameList);
        return logs;
    }

    private Game simulate(Integer id1, Integer id2, boolean print ,List<QuarterLog> logs) {
        TeamWithPlayersAndStatsDTO team1= teamRepository.findTeamWithPlayersById(id1);
        TeamWithPlayersAndStatsDTO team2 = teamRepository.findTeamWithPlayersById(id2);
        List<TeamWithPlayersAndStatsDTO> teams = List.of(team1,team2);

        int team1Score =0;
        int team2Score =0;
        boolean flag = false;

        for(int j=1;j<=4;j++){
            for(int i=0;i<50;i++) {
                PlayerWithStats p1 = selectRandomPlayer(team1);
                PlayerWithStats p2 = selectRandomPlayer(team2);
                if (flag) {
                    team1Score += point(p1, p2);
                } else {
                    team2Score += point(p2, p1);
                }
                flag = !flag;
            }
            //todo view 뺴기
            if(print)
                logs.add(QuarterLog.of(j, team1.getName(), team1Score, team2.getName(), team2Score));
//                View.printQuarterScore(j,team1.getName() ,team1Score, team2.getName(),team2Score);
        }

        boolean result = determineWinner(team1Score, team2Score);

        //todo 여기가 저장지점

        updateTeamStats(team1, team2, result);
        teamRepository.updateAllWinsAndTotalGames(teams);

        return Game.builder()
                .team1(team1.getId())
                .team2(team2.getId())
                .team1Score(team1Score)
                .team2Score(team1Score)
                .result(result)
                .build();
    }

    private PlayerWithStats selectRandomPlayer(TeamWithPlayersAndStatsDTO team) {
        int idx = (int) (Math.random() * team.getPlayers().size());
        return team.getPlayers().get(idx);
    }

    private int point(PlayerWithStats attacker, PlayerWithStats defender) {

        int offenseScore = attacker.getDribble() + attacker.getPass() + attacker.getRebound();
        int defenseScore = defender.getBlock() + defender.getSteal() + defender.getRebound();

        int successRate = (attacker.getShoot() / 2) + (offenseScore - defenseScore) / 5;

        if (Math.random() <= (double) successRate / 100) {
            if (Math.random() <= (double) attacker.getPoint3() / 2) {
                return 3;
            }
            return 2;
        }
        return 0;
    }

    private Game otherTeamDoGame(Set<Integer> set,List<QuarterLog> logs) {
        Iterator<Integer> it= set.iterator();
        return simulate(it.next(),it.next(),false,logs);
    }

    private boolean determineWinner(int team1Score, int team2Score) {
        while (team1Score == team2Score) {
            // 연장전: 10번의 시뮬레이션으로 추가 점수 부여
            for (int i = 0; i < 10; i++) {
                if (Math.random() < 0.5) {
                    team1Score += (int) (Math.random() * 3); // 0~2점
                } else {
                    team2Score += (int) (Math.random() * 3);
                }
            }
        }
        return team1Score > team2Score;
    }

    private void updateTeamStats(TeamWithPlayersAndStatsDTO team1, TeamWithPlayersAndStatsDTO team2, boolean result) {
        if (result) {
            team1.setWins(team1.getWins() + 2);
        } else {
            team2.setWins(team2.getWins() + 2);
        }
        team1.setTotalGames(team1.getTotalGames() + 1);
        team2.setTotalGames(team2.getTotalGames() + 1);
    }
}
