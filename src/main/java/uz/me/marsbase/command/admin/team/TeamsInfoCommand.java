package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.payload.TeamDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.TEAMS;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class TeamsInfoCommand implements Command {
    private static final TeamService teamService = InstanceHolder.getInstance(TeamService.class);
    private static final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<TeamDTO> teamDTOList = teamService.getTeamDTOs();
        if (session.getAttribute(TEAMS) == null)
            session.setAttribute(TEAMS, teamDTOList);

        return new Router(TEAM_PAGE_FOR_ADMIN, REDIRECT);
    }
}
