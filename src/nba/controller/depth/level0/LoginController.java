package nba.controller.depth.level0;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nba.controller.CustomController;
import nba.util.ScanUtil;
import nba.util.View;

@RequiredArgsConstructor
@Getter
public class LoginController implements CustomController {

    private final String name;

    @Override
    public String handle() {
        View.printLogin();
        String next= ScanUtil.readLine();
        if(next.equals("register")){
            return "register";
        }
        //todo 로그인 확인
        boolean result = false;
        if(!result){
            return "login";
        }
        else{
            return "enter";
        }
    }
}
