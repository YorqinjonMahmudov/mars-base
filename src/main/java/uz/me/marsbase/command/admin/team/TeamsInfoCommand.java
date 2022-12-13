package uz.me.marsbase.command.admin.team;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.payload.TeamDTO;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_LEAD_PANEL;
import static uz.me.marsbase.command.navigation.PageNavigation.TEAM_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;

public class TeamsInfoCommand implements Command {
    private static final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute(EDITING_TEAM) != null)
            session.setAttribute(EDITING_TEAM, null);
        UserDTO userDTO = (UserDTO) session.getAttribute(CURRENT_USER);
        if (userDTO.getRole().equals(Role.ADMIN)) {
            List<TeamDTO> teamDTOList = teamService.getTeamDTOs();
            session.setAttribute(TEAMS, teamDTOList);
        } else if (userDTO.getRole().equals(Role.TEAM_LEADER)) {
            List<TeamDTO> teamDTOList = teamService.getTeamDTOsByTeamLeadId(userDTO.getId());
            session.setAttribute(TEAMS, teamDTOList);
            return new Router(TEAM_LEAD_PANEL, FORWARD);
        }
        return new Router(TEAM_PAGE_FOR_ADMIN, FORWARD);
    }
}
