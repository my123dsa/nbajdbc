package nba.controller.depth.level1;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
@Getter
public class EnterController implements CustomController {

    private final String name;

    @Override
    public String handle() {
        View.printEnter();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return "team";
            case 2:
                return "playerEvent";
            default:
                View.printValidNum();
                return "enter";
        }
    }
}
