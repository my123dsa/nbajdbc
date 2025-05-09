package nba.repository;

import lombok.Getter;
import nba.util.ConnectionFactory;

import javax.sql.DataSource;

@Getter
public class GameRepository {
    private final DataSource dataSource;

    public GameRepository( ConnectionFactory factory) {
        this.dataSource= factory.getDataSource();
    }

}
