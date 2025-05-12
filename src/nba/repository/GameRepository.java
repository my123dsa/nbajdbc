package nba.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.domain.Game;
import nba.dto.game.GameResultDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GameRepository {
    private final DataSource dataSource;
    private final HistoryLogger historyLogger;

    public void saveAll(List<Game> games) {

        String sql = "INSERT INTO game (ID,TEAM1_ID,TEAM1_SCORE,TEAM2_ID,TEAM2_SCORE, RESULT) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            List<Integer> ids = fetchNextIds(conn,games.size());

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < games.size(); i++) {
                    Game  game = games.get(i);
                    ps.setInt(1, ids.get(i));
                    ps.setInt(2,game.getTeam1());
                    ps.setInt(3,game.getTeam1Score());
                    ps.setInt(4,game.getTeam2());
                    ps.setInt(5,game.getTeam2Score());
                    ps.setBoolean(6,game.getResult());
                    ps.addBatch();

                    historyLogger.logInsert(conn, "game", ids.get(i), game.toString());
                }

                ps.executeBatch();
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("❌ 업데이트 중 오류 발생", e);

            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> fetchNextIds(Connection conn, int count) throws SQLException {
        String getIdSql = "SELECT game_seq.NEXTVAL FROM dual";
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(getIdSql)) {
            for (int i = 0; i < count; i++) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) throw new SQLException("ID 생성 실패");
                    ids.add(rs.getInt(1));
                }
            }
        }
        return ids;
    }

    public List<GameResultDTO> findByTeamId(int teamId){
        String sql = "SELECT g.id AS game_id, g.team1_score, g.team2_score, g.result, " +
                "g.team1_id, t1.name AS t1_name, t2.name AS t2_name " +
                "FROM game g " +
                "JOIN team t1 ON t1.id = g.team1_id " +
                "JOIN team t2 ON t2.id = g.team2_id " +
                "WHERE t1.id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            List<GameResultDTO> dtos = new ArrayList<>();
            while (rs.next()) {
                GameResultDTO dto = GameResultDTO.builder()
                        .id(rs.getInt("game_id"))
                        .team1Score(rs.getInt("team1_score"))
                        .team2Score(rs.getInt("team2_score"))
                        .result(rs.getBoolean("result"))
                        .teamName1(rs.getString("t1_name"))
                        .teamName2(rs.getString("t2_name"))
                        .build();
                dtos.add(dto);
            }
            return dtos;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
