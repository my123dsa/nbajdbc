package nba.controller.depth.level2;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
public class PlayerEventController implements CustomController {
    @Getter
    private final String name;

    @Override
    public String handle() {
        View.printTeamEvent();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return createPlayer();
            case 2:
                return retirePlayer();
            case 0:
                return "enter";
            default:
                View.printValidNum();
                return "playerEvent";
        }
    }

}
