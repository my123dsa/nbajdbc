package nba.repository;

import nba.util.ConnectionFactory;

import javax.sql.DataSource;

public class PlayerRepository {

    private final DataSource dataSource;

    public PlayerRepository(ConnectionFactory factory) {
        this.dataSource = factory.getDataSource();
    }

}
