package nba.repository;

import nba.util.ConnectionFactory;

import javax.sql.DataSource;

public class StatsRepository {
    private final DataSource dataSource;
    public StatsRepository(ConnectionFactory factory) {

        this.dataSource = factory.getDataSource();
    }
}
