package nba.service;


import lombok.RequiredArgsConstructor;
import nba.repository.TeamRepository;

@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
}
