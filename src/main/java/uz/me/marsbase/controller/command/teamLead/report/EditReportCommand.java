package uz.me.marsbase.controller.command.teamLead.report;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dtos.ReportDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.ReportService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.EDITING_REPORT;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class EditReportCommand implements Command {

    private final ReportService reportService = InstanceHolder.getInstance(ReportService.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ReportDTO currentReport = reportService.findById(Integer.valueOf(request.getParameter("currentWorkReportId")));
        session.setAttribute(EDITING_REPORT, currentReport);
        return new Router(WORK_INFO, REDIRECT);
    }
}
