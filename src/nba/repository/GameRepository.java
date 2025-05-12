package nba.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.domain.Game;

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
        String getIdSql = "SELECT game_seq.NEXTVAL FROM dual";
        String sql = "INSERT INTO game (ID,TEAM1_ID,TEAM1_SCORE,TEAM2_ID,TEAM2_SCORE, RESULT) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            List<Integer> ids = new ArrayList<>();
            try (PreparedStatement psGetId = conn.prepareStatement(getIdSql)) {
                for (int i = 0; i < games.size(); i++) {
                    try (ResultSet rs = psGetId.executeQuery()) {
                        if (!rs.next()) throw new SQLException("ID 생성 실패");
                        ids.add(rs.getInt(1));
                    }
                }
            }

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
}
