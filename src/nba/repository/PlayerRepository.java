package nba.repository;

import lombok.RequiredArgsConstructor;
import nba.util.ConnectionFactory;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class PlayerRepository {

    private final DataSource dataSource;
}
