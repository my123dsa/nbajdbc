package nba.controller.depth.level3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

import java.util.List;

@RequiredArgsConstructor
public class GameController implements CustomController {
    @Getter
    private final String name;

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

}
