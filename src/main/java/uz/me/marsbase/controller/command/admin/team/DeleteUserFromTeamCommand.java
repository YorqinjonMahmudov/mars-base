package uz.me.marsbase.controller.command.admin.team;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class DeleteUserFromTeamCommand implements Command {
    TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        var session = request.getSession();
        Integer teamId = (Integer) session.getAttribute(AttributeParameterHolder.PARAMETER_TEAM_ID);
        if (teamService.deleteUser(Integer.parseInt(request.getParameter("deletingUserId")),
                teamId)) {
            List<UserDTO> users = teamService.getUsersByTeamId(teamId);
            session.setAttribute(AttributeParameterHolder.USERS, users);
        }
        return new Router(PageNavigation.TEAM_MEMBERS_PAGE, REDIRECT);
    }
}
