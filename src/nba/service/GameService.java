package nba.service;

import lombok.RequiredArgsConstructor;
import nba.repository.GameRepository;
import nba.repository.TeamRepository;

@RequiredArgsConstructor
public class GameService {
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;

}
