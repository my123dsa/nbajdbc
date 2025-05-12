package nba.controller.depth.level0;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.controller.MainController;
import nba.dto.OwnerState;
import nba.service.LoginService;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
public class LoginController implements CustomController {
    @Getter
    private final String name;
    private final LoginService loginService;

    @Override
    public String handle() {
        View.printLogin();
        String next = ScanUtil.readLine();
        if (next.equals("register")) {
            return "register";
        }

        OwnerState ownerState = loginService.findByIdAndPassword(next);
        if (ownerState == null) {
            return "login";
        }

        MainController.setOwnerState(ownerState);

        return "enter";
    }
}
