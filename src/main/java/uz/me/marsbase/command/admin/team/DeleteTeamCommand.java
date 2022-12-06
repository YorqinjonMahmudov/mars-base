package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.TEAMS;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class DeleteTeamCommand implements Command {
    TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = TEAM_PAGE_FOR_ADMIN;
        var session = request.getSession();
        if (teamService.delete(Integer.parseInt(request.getParameter("deletingTeamId")))) {
            var teamDTOs = teamService.getTeamDTOs();
            session.setAttribute(TEAMS, teamDTOs);
        }
        return new Router(page, REDIRECT);
    }
}
