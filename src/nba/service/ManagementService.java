package nba.service;



import lombok.RequiredArgsConstructor;
import nba.repository.PlayerRepository;
import nba.repository.TeamRepository;

import java.util.List;

@RequiredArgsConstructor
public class ManagementService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

}
