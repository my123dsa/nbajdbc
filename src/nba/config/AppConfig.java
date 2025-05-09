package nba.config;



import nba.controller.MainController;
import nba.controller.depth.level.InitController;
import nba.controller.depth.level0.LoginController;
import nba.controller.depth.level0.RegisterController;
import nba.controller.depth.level1.EnterController;
import nba.controller.depth.level2.PlayerEventController;
import nba.controller.depth.level2.TeamController;
import nba.controller.depth.level3.GameController;
import nba.controller.depth.level3.ManagementController;
import nba.controller.depth.level3.PlayerController;
import nba.domain.Owner;
import nba.repository.*;
import nba.service.GameService;
import nba.service.ManagementService;
import nba.service.PlayerService;
import nba.service.TeamService;
import nba.util.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppConfig {
    public MainController initMainController() {
        ConnectionFactory factory= new ConnectionFactory();
//        Owner owner = createOwner(factory);

        GameRepository gameRepository = new GameRepository(factory);
        HeadCoachRepository headCoachRepository = new HeadCoachRepository(factory);
        PlayerRepository playerRepository = new PlayerRepository(factory);
        TeamRepository teamRepository = new TeamRepository(factory,playerRepository);
        StatsRepository statsRepository = new StatsRepository(factory);

        PlayerService playerService = new PlayerService(playerRepository, teamRepository,statsRepository);
        GameService gameService = new GameService(teamRepository, gameRepository);
        ManagementService managementService = new ManagementService(teamRepository, playerRepository);
        TeamService teamService = new TeamService(teamRepository);

        InitController initController = new InitController("init");
        LoginController loginController = new LoginController("login");
        RegisterController registerController = new RegisterController("register");
        PlayerController playerController = new PlayerController("player", playerService, teamService);
        ManagementController managementController = new ManagementController("management", playerService, teamService, managementService);
        GameController gameController = new GameController("game", gameService);

        TeamController teamController = new TeamController("team");
        PlayerEventController playerEventController = new PlayerEventController("playerEvent", playerService);
        EnterController enterController = new EnterController("enter");

        return new MainController(
                initController,
                loginController,
                registerController,
                enterController,
                teamController,
                playerEventController,
                playerController,
                managementController,
                gameController
        );
    }
//
//    public Owner createOwner(ConnectionFactory factory) {
//        while (true) {
//            View.printInit();
//            int next = ScanUtil.readInt();
//            System.out.println(next + "");
//            if(next >=1 && next <=4) {
//                String sql= "select * from oner where id=?";
//                try(PreparedStatement ps= factory.getConnection().prepareStatement(sql)) {
//                    ps.setInt(1, next);
//                    ResultSet rs = ps.executeQuery();
//                    if(!rs.next()) {
//                        throw  new RuntimeException("입력값 오류");
//                    }
//                    return Owner.builder()
//                            .teamId(rs.getInt("team_id"))
//                            .userType(rs.getString("user_type"))
//                            .build();
//                }catch (SQLException e){
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
}