package nba.controller.depth.level3;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

import java.util.List;

@RequiredArgsConstructor
public class ManagementController implements CustomController {
    @Getter
    private final String name;

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
}
