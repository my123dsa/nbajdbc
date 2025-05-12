package nba.repository;

import lombok.RequiredArgsConstructor;
import nba.dto.owner.LoginDTO;
import nba.dto.owner.OwnerState;
import nba.dto.owner.RegisterDTO;

import javax.sql.DataSource;
import java.sql.*;

@RequiredArgsConstructor
public class OwnerRepository {
    public final DataSource dataSource;
    public final HistoryLogger historyLogger;

    public OwnerState findByIdAndPassword(LoginDTO loginDTO) {
        String sql = "SELECT * FROM owner WHERE email = ? AND password = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, loginDTO.getEmail());
            ps.setString(2, loginDTO.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return OwnerState.builder()
                            .teamId(rs.getInt("team_id"))
                            .userType(rs.getString("user_type"))
                            .build();
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find owner by email and password", e);
        }
    }

    public int register(RegisterDTO registerDTO) {
        String getIdSql = "SELECT owner_seq.NEXTVAL FROM dual";
        String insertSql = "INSERT INTO owner(id, email, password, user_type,TEAM_ID) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            int newId;
            try (PreparedStatement psGetId = conn.prepareStatement(getIdSql);
                 ResultSet rs = psGetId.executeQuery()) {
                if (!rs.next())
                    throw new SQLException("Failed to retrieve new owner ID");
                newId = rs.getInt(1);
            }

            try (PreparedStatement psInsert = conn.prepareStatement(insertSql)) {
                psInsert.setInt(1, newId);
                psInsert.setString(2, registerDTO.getEmail());
                psInsert.setString(3, registerDTO.getPassword());
                psInsert.setString(4, "User");
                psInsert.setInt(5, registerDTO.getTeamId());


                int result = psInsert.executeUpdate();

                historyLogger.logInsert(conn, "owner", newId, registerDTO.toString());

                conn.commit();
                return result;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error inserting owner", e);
            }
            finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ DB 연결 또는 트랜잭션 처리 중 오류", e);
        }
    }
}