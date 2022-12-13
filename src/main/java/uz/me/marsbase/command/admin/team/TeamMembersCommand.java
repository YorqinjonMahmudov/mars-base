package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.PARAMETER_TEAM_ID;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_MEMBERS_PAGE;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;

public class TeamMembersCommand implements Command {

    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = TEAM_MEMBERS_PAGE;
        HttpSession session = request.getSession();
        String teamId = request.getParameter(PARAMETER_TEAM_ID);

        if (teamId == null)
            page = TEAM_PAGE_FOR_ADMIN;
        else {
            session.setAttribute(PARAMETER_TEAM_ID, Integer.parseInt(teamId));
            List<UserDTO> users = teamService.getUsersByTeamId(Integer.parseInt(teamId));
            session.setAttribute(AttributeParameterHolder.USERS, users);
        }
        return new Router(page, FORWARD);
    }
}
