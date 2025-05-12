package nba.controller.depth.level4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.dto.game.GameResultDTO;
import nba.dto.game.QuarterLog;
import nba.dto.game.RankDTO;
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
        List<QuarterLog> logs = gameService.doGame();
        View.printQuarterScore(logs);
        return "game";
    }

    private String gameList() {
        List<GameResultDTO> gameList = gameService.getGameList();
        View.printGetGameList(gameList);
        return "game";
    }

    private String rank() {
        List<RankDTO> teams = gameService.getRank();
        View.printGetRank(teams);
        return "game";
    }
}
