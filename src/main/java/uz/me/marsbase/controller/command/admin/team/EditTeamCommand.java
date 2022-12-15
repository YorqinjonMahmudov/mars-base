package uz.me.marsbase.controller.command.admin.team;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.model.dtos.TeamDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class EditTeamCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeParameterHolder.USERS) == null)
            session.setAttribute(AttributeParameterHolder.USERS, userService.getUsers());
        Integer editingTeamId = Integer.valueOf(request.getParameter("editingTeamId"));
        Team editingTeam = teamService.getTeamById(editingTeamId);
        var teamDTO = new TeamDTO(editingTeam.getId(),
                editingTeam.getName(),
                userService.getUserById(editingTeam.getTeamLeadId()).getEmail(),
                editingTeam.isActive());
        session.setAttribute(AttributeParameterHolder.EDITING_TEAM, teamDTO);
        return new Router(PageNavigation.TEAM_PAGE_FOR_ADMIN, REDIRECT);
    }
}
