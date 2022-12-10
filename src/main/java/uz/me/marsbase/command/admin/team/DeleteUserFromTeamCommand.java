package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.PARAMETER_TEAM_ID;
import static uz.me.marsbase.command.navigation.AttributeParameterHolder.USERS;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_MEMBERS_PAGE;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class DeleteUserFromTeamCommand implements Command {
    TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        var session = request.getSession();
        Integer teamId = (Integer) session.getAttribute(PARAMETER_TEAM_ID);
        if (teamService.deleteUser(Integer.parseInt(request.getParameter("deletingUserId")),
                teamId)) {
            var users = teamService.getUsersByTeamId(teamId);
            session.setAttribute(USERS, users);
        }
        return new Router(TEAM_MEMBERS_PAGE, REDIRECT);
    }
}
