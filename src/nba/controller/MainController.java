package nba.controller;


import lombok.Getter;
import lombok.Setter;
import nba.dto.owner.OwnerState;

import java.util.HashMap;
import java.util.Map;


public class MainController {
    @Getter
    @Setter
    private static OwnerState ownerState;
    private final Map<String, CustomController> controllerMap;

    public MainController(CustomController... controllers) {
        controllerMap=new HashMap<>();
    // 모든 컨트롤러를 map에 등록
        for (CustomController controller : controllers) {
            controllerMap.put(controller.getName(), controller);
        }
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