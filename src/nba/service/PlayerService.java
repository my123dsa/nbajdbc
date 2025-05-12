package nba.service;


import lombok.RequiredArgsConstructor;
import nba.domain.Stats;
import nba.repository.PlayerRepository;
import nba.repository.StatsRepository;
import nba.repository.TeamRepository;

import java.util.List;

@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final StatsRepository statsRepository;

    public Stats getStats(String playerName) {
        return playerRepository.findStatsByName(playerName);
    }
}
