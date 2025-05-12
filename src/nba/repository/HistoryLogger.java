package nba.repository;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class HistoryLogger {

    public void logInsert(Connection conn, String tableName, int rowId, String content) {
        log(conn,tableName, rowId, "INSERT", content);
    }

    public void logUpdate(Connection conn,String tableName, int rowId, String content) {
        log(conn,tableName, rowId, "UPDATE", content);
    }

    public void logDelete(Connection conn,String tableName, int rowId, String content) {
        log(conn,tableName, rowId, "DELETE", content);
    }

    private void log(Connection conn,String tableName, int rowId, String operation, String content) {
        String sql = "INSERT INTO history(id,table_name, row_id, operation, change_content) " +
                "VALUES (HISTORY_SEQ.NEXTVAL ,?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println("Inserting " + operation + " into " + tableName);
            ps.setString(1, tableName);
            ps.setInt(2, rowId);
            ps.setString(3, operation);
            ps.setString(4, content);
            ps.executeUpdate();

        } catch (SQLException e) {
            // 필요하다면 로그만 남기고 예외 무시할 수도 있음
            throw new RuntimeException("History logging failed", e);
        }
    }
}