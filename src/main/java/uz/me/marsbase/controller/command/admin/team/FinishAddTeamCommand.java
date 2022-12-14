package uz.me.marsbase.controller.command.admin.team;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.validator.AddTeamValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.ADD_TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class FinishAddTeamCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = TEAM_PAGE_FOR_ADMIN;
        HttpSession session = request.getSession();
        FormValidator addTeamValidator = new AddTeamValidator();
        var parameterMap = request.getParameterMap();
        var validatedResult = addTeamValidator.validate(parameterMap);
        if (validatedResult.isEmpty()) {
            String teamName = request.getParameter(PARAMETER_TEAM_NAME);
            String teamLeadEmail = request.getParameter(PARAMETER_TEAM_LEAD_EMAIL);
            UserDTO user = userService.getUserByEmail(teamLeadEmail);
            var team = new Team(teamName, user.getId(), true);
            if (teamService.insert(team)) {
                userService.makeTeamLead(user.getId());
                page = TEAM_PAGE_FOR_ADMIN;
                session.setAttribute(TEAMS, teamService.getTeamDTOs());
            }
        } else {
            session.setAttribute(INVALID_FORM, validatedResult);
            page = ADD_TEAM_PAGE_FOR_ADMIN;
        }
        return new Router(page, REDIRECT);
    }
}
