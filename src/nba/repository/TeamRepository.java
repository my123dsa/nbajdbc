package nba.repository;

import lombok.RequiredArgsConstructor;
import nba.util.ConnectionFactory;

import javax.sql.DataSource;


@RequiredArgsConstructor
public class TeamRepository {
    private final DataSource dataSource;
    private final PlayerRepository playerRepository;
}