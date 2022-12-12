package uz.me.marsbase.command.admin.work;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.payload.WorkViewDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class WorkViewCommand implements Command {
    private static final WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<WorkViewDTO> workViews = workService.getWorks();
        session.setAttribute(WORK_VIEWS, workViews);
        session.setAttribute(CURRENT_WORK,null);
        session.setAttribute(CURRENT_WORK_REPORT,null);

        return new Router(WORK_PAGE_FOR_ADMIN, REDIRECT);
    }

}
