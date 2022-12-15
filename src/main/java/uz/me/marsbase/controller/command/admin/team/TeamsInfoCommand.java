package uz.me.marsbase.controller.command.admin.team;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.model.dtos.TeamDTO;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.controller.router.Router.PageChangeType.FORWARD;

public class TeamsInfoCommand implements Command {
    private static final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeParameterHolder.EDITING_TEAM) != null)
            session.setAttribute(AttributeParameterHolder.EDITING_TEAM, null);
        UserDTO userDTO = (UserDTO) session.getAttribute(AttributeParameterHolder.CURRENT_USER);
        if (userDTO.getRole().equals(Role.ADMIN)) {
            List<TeamDTO> teamDTOList = teamService.getTeamDTOs();
            session.setAttribute(AttributeParameterHolder.TEAMS, teamDTOList);
        } else if (userDTO.getRole().equals(Role.TEAM_LEADER)) {
            List<TeamDTO> teamDTOList = teamService.getTeamDTOsByTeamLeadId(userDTO.getId());
            session.setAttribute(AttributeParameterHolder.TEAMS, teamDTOList);
            return new Router(PageNavigation.TEAM_LEAD_PANEL, FORWARD);
        }
        return new Router(PageNavigation.TEAM_PAGE_FOR_ADMIN, FORWARD);
    }
}
