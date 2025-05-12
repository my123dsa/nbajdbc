package nba.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.util.ConnectionFactory;

import javax.sql.DataSource;

@Getter
@RequiredArgsConstructor
public class GameRepository {
    private final DataSource dataSource;

}
