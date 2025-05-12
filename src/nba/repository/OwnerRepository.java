package nba.repository;

import lombok.RequiredArgsConstructor;
import nba.dto.LoginDTO;
import nba.dto.OwnerState;

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

    public int register(LoginDTO loginDTO) {
        String getIdSql = "SELECT owner_seq.NEXTVAL FROM dual";
        String insertSql = "INSERT INTO owner(id, email, password, user_type) VALUES (?, ?, ?, ?)";

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
                psInsert.setString(2, loginDTO.getEmail());
                psInsert.setString(3, loginDTO.getPassword());
                psInsert.setString(4, loginDTO.getUserType());

                int result = psInsert.executeUpdate();

                historyLogger.logInsert(conn, "owner", newId, loginDTO.toString());

                conn.commit();
                return result;
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error inserting owner", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error during registration", e);
        }
    }
}
//
//    public int register(LoginDTO loginDTO) {
//        String getIdSql = "SELECT owner_seq.NEXTVAL FROM dual";
//        String sql = "INSERT INTO owner(id,email, password,USER_TYPE) VALUES(?,?,?,?)";
//
//        Connection conn = null;
//        PreparedStatement ps =null;
//        PreparedStatement ps2 =null;
////        CallableStatement cs= null;
//
//        try{
//            conn =dataSource.getConnection();
//            ps =conn.prepareStatement(sql);
//            ps2 =conn.prepareStatement(getIdSql);
////            cs = conn.prepareCall(sql);
//            conn.setAutoCommit(false);
//
//            ResultSet rs = ps2.executeQuery();
//            int newId = -1;
//            if (rs.next()) {
//                newId = rs.getInt(1);
//            }
//
//
//            ps.setInt(1,newId);
//            ps.setString(2,loginDTO.getEmail());
//            ps.setString(3,loginDTO.getPassword());
//            ps.setString(4,loginDTO.getUserType());
//
//
//            int result =ps.executeUpdate();
//
//            historyLogger.logInsert(conn,"owner", newId, loginDTO.toString());
//
//            conn.commit();
//            return result;
//        }catch (SQLException e){
//            if (conn != null) {
//                try {
//                    conn.rollback();
//                } catch (SQLException rollbackEx) {
//                    rollbackEx.printStackTrace(); // 또는 로깅
//                }
//            }
//            throw new RuntimeException(e);
//        }
//        finally {
//            if (ps != null) try { ps.close();
//
//            } catch (SQLException ignored) {}
//            if (conn != null) {
//                try {
//                    conn.setAutoCommit(true);
//                    conn.close();
//                } catch (SQLException ignored) {}
//            }
//        }
//    }
//}
