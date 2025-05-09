package nba.controller.depth.level0;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
@Getter
public class RegisterController implements CustomController {

    private final String name;

    @Override
    public String handle() {
        View.printRegister();
        String next= ScanUtil.readLine();
        //todo  등록 프로세스
        return "login";
    }
}
