package nba.controller.depth.level2;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
public class TeamController implements CustomController {
    @Getter
    private final String name;

    @Override
    public String handle() {
        View.printTeam();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return "player";
            case 2:
                return "management";
            case 3:
                return "game";
            case 0:
                return "enter";
            default:
                View.printValidNum();
                return "team";
        }
    }
}
