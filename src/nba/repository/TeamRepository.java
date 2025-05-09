package nba.repository;

import nba.util.ConnectionFactory;

import javax.sql.DataSource;


public class TeamRepository {
    private final DataSource dataSource;
    private final PlayerRepository playerRepository;

    public TeamRepository(ConnectionFactory factory, PlayerRepository playerRepository) {
        this.dataSource = factory.getDataSource();
        this.playerRepository = playerRepository;
    }
}