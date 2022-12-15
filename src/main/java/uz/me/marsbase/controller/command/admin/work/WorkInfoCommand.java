package uz.me.marsbase.controller.command.admin.work;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dtos.ReportDTO;
import uz.me.marsbase.model.dtos.WorkDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.ReportService;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class WorkInfoCommand implements Command {
    private static final WorkService workService = InstanceHolder.getInstance(WorkService.class);
    private static final ReportService reportService = InstanceHolder.getInstance(ReportService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = WORK_PAGE_FOR_ADMIN;
        HttpSession session = request.getSession();
        String currenWorkId = request.getParameter(CURRENT_WORK_ID);
        session.setAttribute(EDITING_WORK, null);
        session.setAttribute(EDITING_REPORT, null);


        if (currenWorkId != null) {

            int curWorkId = Integer.parseInt(currenWorkId);
            WorkDTO workDTO = workService.findById(curWorkId);
            ReportDTO reportDTO = reportService.findByWorkId(curWorkId);

            session.setAttribute(CURRENT_WORK, workDTO);

            session.setAttribute(CURRENT_WORK_REPORT, reportDTO);
            page = WORK_INFO;
            return new Router(page, REDIRECT);
        } else
            return new Router(page, REDIRECT);

    }
}
