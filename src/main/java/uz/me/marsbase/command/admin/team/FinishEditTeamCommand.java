package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.validator.AddTeamValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class FinishEditTeamCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        FormValidator validator = new AddTeamValidator();
        var parameterMap = request.getParameterMap();
        var validateResult = validator.validate(parameterMap);
        if (validateResult.isEmpty()) {
            Integer editingTeamId = Integer.valueOf(request.getParameter("editingTeamId"));
            Team editingTeam = teamService.getTeamById(editingTeamId);
            String teamName = request.getParameter(PARAMETER_TEAM_NAME);
            String teamLeadEmail = request.getParameter(PARAMETER_TEAM_LEAD_EMAIL);
            var team = new Team(teamName, userService.getUserByEmail(teamLeadEmail).getId(), true);
            boolean update = teamService.update(editingTeamId, team);
            if (update){
                session.setAttribute(TEAMS,teamService.getTeamDTOs());
                session.setAttribute(EDITING_TEAM,null);
            }

        }

        return new Router(TEAM_PAGE_FOR_ADMIN, REDIRECT);
    }
}
