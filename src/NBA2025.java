import nba.config.AppConfig;
import nba.controller.MainController;

public class NBA2025 {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MainController mainController = appConfig.initMainController();
        mainController.play("init");
    }
}
