package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.payload.TeamDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.EDITING_TEAM;
import static uz.me.marsbase.command.navigation.AttributeParameterHolder.USERS;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class EditTeamCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(USERS) == null)
            session.setAttribute(USERS, userService.getUsers());
        Integer editingTeamId = Integer.valueOf(request.getParameter("editingTeamId"));
        Team editingTeam = teamService.getTeamById(editingTeamId);
        var teamDTO = new TeamDTO(editingTeam.getId(),
                editingTeam.getName(),
                userService.getUserById(editingTeam.getTeamLeadId()).getEmail(),
                editingTeam.isActive());
        session.setAttribute(EDITING_TEAM, teamDTO);
        return new Router(TEAM_PAGE_FOR_ADMIN, REDIRECT);
    }
}
