package nba.controller.depth.level;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
public class InitController implements CustomController {
    @Getter
    private final String name;

    @Override
    public String handle() {
        View.printInitWithControl();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return "login";
            case 2:
                return "register";
            default:
                View.printValidNum();
                return "init";
        }
    }
}
