package uz.me.marsbase.controller.command.admin.team;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.controller.router.Router.PageChangeType.FORWARD;

public class TeamMembersCommand implements Command {

    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = PageNavigation.TEAM_MEMBERS_PAGE;
        HttpSession session = request.getSession();
        String teamId = request.getParameter(AttributeParameterHolder.PARAMETER_TEAM_ID);

        if (teamId == null)
            page = PageNavigation.TEAM_PAGE_FOR_ADMIN;
        else {
            session.setAttribute(AttributeParameterHolder.PARAMETER_TEAM_ID, Integer.parseInt(teamId));
            List<UserDTO> users = teamService.getUsersByTeamId(Integer.parseInt(teamId));
            session.setAttribute(AttributeParameterHolder.USERS, users);
        }
        return new Router(page, FORWARD);
    }
}
