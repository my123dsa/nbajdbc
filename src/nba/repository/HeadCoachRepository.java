package nba.repository;


import lombok.Getter;
import nba.util.ConnectionFactory;


import javax.sql.DataSource;

@Getter
public class HeadCoachRepository {
    private final DataSource dataSource;

    public HeadCoachRepository(ConnectionFactory factory) {
        this.dataSource = factory.getDataSource();
    }

}
