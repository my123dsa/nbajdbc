package nba.controller.depth.level3;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.service.ManagementService;
import nba.service.PlayerService;
import nba.service.TeamService;
import nba.util.ScanUtil;
import nba.util.View;

import java.util.List;

@RequiredArgsConstructor
public class ManagementController implements CustomController {
    @Getter
    private final String name;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final ManagementService managementService;


    @Override
    public String handle() {
        View.printManagement();
        int next = ScanUtil.readInt();
        switch (next) {
            case 1:
                return trade();
            case 2:
                return buyPlayer();
            case 3:
                return sellPlayer();
            case 4:
                return changeHead();
            case 0:
                return "team";
            default:
                return "management";
        }
    }

    private String changeHead() {
        return null;
    }

    private String sellPlayer() {
        return null;
    }

    private String buyPlayer() {
        return null;
    }

    private String trade() {
        return null;
    }
}
