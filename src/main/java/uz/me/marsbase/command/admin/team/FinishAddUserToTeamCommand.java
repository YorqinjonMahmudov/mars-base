package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.utils.validator.CheckEmailValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.*;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class FinishAddUserToTeamCommand implements Command {

    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = TEAM_MEMBERS_PAGE;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        var parameterMap = request.getParameterMap();

        FormValidator formValidator = new CheckEmailValidator();
        Map<String, String> validated = formValidator.validate(parameterMap);

        if (validated.isEmpty()) {
//            UserDTO userDTO = (UserDTO) session.getAttribute(CURRENT_USER);
//            if (userDTO.getRole().equals(Role.TEAM_LEADER))
//                page=TEAM_MEMBERS_PAGE_FOR_TEAM_LEAD;


            Integer teamId = (Integer) session.getAttribute(PARAMETER_TEAM_ID);
            var email = request.getParameter("email");
            var hasInserted = teamService.insertUserToTeam(teamId, email);
            if (!hasInserted) {
                page = ADD_USER_PAGE;
                type = FORWARD;
            } else {
                List<UserDTO> users = teamService.getUsersByTeamId(teamId);
                session.setAttribute(USERS, users);
            }

        } else {
            session.setAttribute(INVALID_FORM, validated);
        }
        return new Router(page, type);
    }
}
