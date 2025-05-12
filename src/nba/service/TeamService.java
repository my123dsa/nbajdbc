package nba.service;


import lombok.RequiredArgsConstructor;
import nba.dto.team.TeamWithPlayersAndStatsDTO;
import nba.repository.TeamRepository;

@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamWithPlayersAndStatsDTO findTeamWithPlayersById(int teamId) {
        return teamRepository.findTeamWithPlayersById(teamId);
    }
}
