package nba.repository;

import lombok.RequiredArgsConstructor;
import nba.domain.Stats;
import nba.util.ConnectionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class PlayerRepository {

    private final DataSource dataSource;

    public Stats findStatsByName(String playerName) {
        String sql = "SELECT s.id as s_id,s.SHOOT,s.PASS,s.DRIBBLE,s.REBOUND,s.BLOCK,s.STEAL,s.POINT3, s.PLAYER_ID as s_player_id " +
                "FROM PLAYER p " +
                "Join STATS s on p.id= s.PLAYER_ID " +
                " WHERE p.NAME = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, playerName);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(!rs.next()) {
                return null;
            }
            return Stats.builder()
                    .shoot(rs.getInt("shoot"))
                    .pass(rs.getInt("pass"))
                    .dribble(rs.getInt("dribble"))
                    .rebound(rs.getInt("rebound"))
                    .block(rs.getInt("block"))
                    .steal(rs.getInt("steal"))
                    .point3(rs.getInt("point3"))
                    .id(rs.getInt("s_id"))
                    .playerId(rs.getInt("s_player_id"))
                    .build();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
