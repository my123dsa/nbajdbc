package nba.controller.depth.level4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.service.GameService;
import nba.util.ScanUtil;
import nba.util.View;

import java.util.List;

@RequiredArgsConstructor
public class GameController implements CustomController {
    @Getter
    private final String name;
    private final GameService gameService;

    @Override
    public String handle() {
        View.printGame();
        int next = ScanUtil.readInt();
        switch (next) {
            case 1:
                return rank();
            case 2:
                return gameList();
            case 3:
                return doGame();
            case 0:
                return "team";
            default:
                View.printValidNum();
                return "game";
        }
    }

    private String doGame() {
        return null;
    }

    private String gameList() {
        return null;
    }

    private String rank() {
        List<RankDTO> teams = gameService.getRank();
        View.printGetRank(teams);
        return "game";
    }
}
