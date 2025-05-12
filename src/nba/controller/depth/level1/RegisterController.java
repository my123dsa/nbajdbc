package nba.controller.depth.level1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.service.LoginService;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
public class RegisterController implements CustomController {
    @Getter
    private final String name;
    private final LoginService loginService;
    @Override
    public String handle() {
        View.printSelectTeam();
        View.printRegister();
        String next= ScanUtil.readLine();
        //todo  등록 프로세스
        boolean result =loginService.register(next);
        if(!result) {
            return "register";
        }
        return "login";
    }
}
