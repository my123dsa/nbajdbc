package nba.controller.depth.level3;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

import java.util.List;

@RequiredArgsConstructor
public class PlayerController implements CustomController {
    @Getter
    private final String name;


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
}
