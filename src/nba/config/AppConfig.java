package nba.config;


import nba.controller.MainController;
import nba.controller.depth.level0.InitController;
import nba.controller.depth.level1.LoginController;
import nba.controller.depth.level1.RegisterController;
import nba.controller.depth.level2.EnterController;
import nba.controller.depth.level3.PlayerEventController;
import nba.controller.depth.level3.TeamController;
import nba.controller.depth.level4.GameController;
import nba.controller.depth.level4.ManagementController;
import nba.controller.depth.level4.PlayerController;
import nba.repository.*;
import nba.service.*;
import nba.util.ConnectionFactory;

import javax.sql.DataSource;

public class AppConfig {
    public MainController initMainController() {
        DataSource dataSource= new ConnectionFactory().getDataSource();
        HistoryLogger historyLogger= new HistoryLogger();

        OwnerRepository ownerRepository = new OwnerRepository(dataSource, historyLogger);
        PlayerRepository playerRepository = new PlayerRepository(dataSource);
        TeamRepository teamRepository = new TeamRepository(dataSource, playerRepository,historyLogger);
        StatsRepository statsRepository = new StatsRepository(dataSource);
        GameRepository gameRepository = new GameRepository(dataSource,historyLogger);
        HeadCoachRepository headCoachRepository = new HeadCoachRepository(dataSource);

        PlayerService playerService = new PlayerService(playerRepository, teamRepository, statsRepository);
        GameService gameService = new GameService(teamRepository, gameRepository);
        ManagementService managementService = new ManagementService(teamRepository, playerRepository);
        TeamService teamService = new TeamService(teamRepository);
        LoginService loginService = new LoginService(ownerRepository);

        return new MainController(
                new InitController("init"),
                new LoginController("login", loginService),
                new RegisterController("register", loginService),
                new EnterController("enter"),
                new TeamController("team"),
                new PlayerEventController("playerEvent", playerService),
                new PlayerController("player", playerService, teamService),
                new ManagementController("management", playerService, teamService, managementService),
                new GameController("game", gameService)
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