package uz.me.marsbase.controller.command.admin.work;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.model.dtos.WorkViewDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.controller.command.navigation.PageNavigation.WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class WorkViewCommand implements Command {
    private static final WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(AttributeParameterHolder.CURRENT_USER);
        List<WorkViewDTO> workViews;
        if (userDTO.getRole().equals(Role.TEAM_LEADER)) {
            workViews = workService.getByTeamLeadId(userDTO.getId());
        } else
            workViews = workService.getWorks();
        session.setAttribute(AttributeParameterHolder.WORK_VIEWS, workViews);
        session.setAttribute(AttributeParameterHolder.CURRENT_WORK, null);
        session.setAttribute(AttributeParameterHolder.CURRENT_WORK_REPORT, null);

        return new Router(WORK_PAGE_FOR_ADMIN, REDIRECT);
    }

}
