package nba.controller.depth.level4;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.service.PlayerService;
import nba.service.TeamService;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
public class PlayerController implements CustomController {
    @Getter
    private final String name;
    private final PlayerService playerService;
    private final TeamService teamService;

    @Override
    public String handle() {
        View.printPlayer();
        int next = ScanUtil.readInt();
        switch (next) {
            case 1:
                return getPlayerList();
            case 2:
                return getPlayerStats();
            case 0:
                return "team";
            default:
                View.printValidNum();
                return "player";
        }
    }

    private String getPlayerStats() {
        return null;
    }

    private String getPlayerList() {
        return null;
    }
}
