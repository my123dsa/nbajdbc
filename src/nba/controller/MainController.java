package nba.controller;


import lombok.Getter;
import lombok.Setter;
import nba.controller.depth.level.InitController;
import nba.controller.depth.level0.LoginController;
import nba.controller.depth.level0.RegisterController;
import nba.controller.depth.level1.*;
import nba.controller.depth.level2.*;
import nba.controller.depth.level3.*;
import nba.domain.Owner;

import java.util.HashMap;
import java.util.Map;


public class MainController {
    @Getter
    @Setter
    private static Owner owner;
    private final Map<String, CustomController> controllerMap = new HashMap<>();

    public MainController(
                          InitController initController,
                          LoginController loginController,
                          RegisterController registerController,
                          EnterController enterController,
                          TeamController teamController,
                          PlayerEventController playerEventController,
                          PlayerController playerController,
                          ManagementController managementController,
                          GameController gameController) {

        // 모든 컨트롤러를 map에 등록
        controllerMap.put(initController.getName(), initController);
        controllerMap.put(loginController.getName(), loginController);
        controllerMap.put(registerController.getName(), registerController);
        controllerMap.put(enterController.getName(), enterController);
        controllerMap.put(teamController.getName(), teamController);
        controllerMap.put(playerEventController.getName(), playerEventController);
        controllerMap.put(playerController.getName(), playerController);
        controllerMap.put(managementController.getName(), managementController);
        controllerMap.put(gameController.getName(), gameController);
    }

    public void play(String screen) {
        while (true) {
            CustomController controller = controllerMap.get(screen); // 예: initController.getName()이 "init"이라면
            if (controller != null) {
                screen = controller.handle();
            }
        }
    }
}