package nba.repository;

import lombok.RequiredArgsConstructor;
import nba.dto.player.PlayerWithStats;
import nba.dto.team.TeamWithPlayersAndStatsDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class TeamRepository {
    private final DataSource dataSource;
    private final PlayerRepository playerRepository;

    public TeamWithPlayersAndStatsDTO findTeamWithPlayersById(int teamId) {
        String sql = "SELECT " +
                "t.id as team_id, t.name as team_name, t.money, t.wins, t.total_games, " +
                "p.id as player_id, p.name as player_name, p.birth, p.salary, p.height, p.wing_span, p.position, p.IS_FA, " +
                "s.shoot, s.pass, s.dribble, s.rebound, s.block, s.steal, s.point3 " +
                "FROM team t " +
                "JOIN player p ON p.TEAM_ID = t.ID " +
                "JOIN stats s ON s.PLAYER_ID = p.ID " +
                "WHERE t.ID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();

            TeamWithPlayersAndStatsDTO team = null;
            List<PlayerWithStats> players = new ArrayList<>();

            while (rs.next()) {
                if(team == null) {
                    System.out.println("팀 있습니다");
                    team = TeamWithPlayersAndStatsDTO.builder()
                            .id(rs.getInt("team_id"))
                            .name(rs.getString("team_name"))
                            .money(rs.getLong("money"))
                            .wins(rs.getInt("wins"))
                            .totalGames(rs.getInt("total_games"))
                            .players(players)
                            .build();
                }

                PlayerWithStats player=  PlayerWithStats.builder()
                        .id(rs.getInt("player_id"))
                        .name(rs.getString("player_name"))
                        .birth(rs.getDate("birth").toLocalDate())
                        .salary(rs.getLong("salary"))
                        .height(rs.getInt("height"))
                        .wingSpan(rs.getInt("wing_span"))
                        .position(rs.getString("position"))
                        .isFA(rs.getBoolean("IS_FA"))
                        .shoot(rs.getInt("shoot"))
                        .pass(rs.getInt("pass"))
                        .dribble(rs.getInt("dribble"))
                        .rebound(rs.getInt("rebound"))
                        .block(rs.getInt("block"))
                        .steal(rs.getInt("steal"))
                        .point3(rs.getInt("point3"))
                        .build();
                players.add(player);
            }

            return team;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}