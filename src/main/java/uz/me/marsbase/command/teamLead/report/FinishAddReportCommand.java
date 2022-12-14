package uz.me.marsbase.command.teamLead.report;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.payload.ReportDTO;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.payload.WorkDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.ReportService;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_REPORT_PAGE;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class FinishAddReportCommand implements Command {

    private final ReportService reportService = InstanceHolder.getInstance(ReportService.class);
    private final WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO currentUser = (UserDTO) session.getAttribute(CURRENT_USER);

        if (currentUser.getRole().equals(Role.TEAM_LEADER) || currentUser.getRole().equals(Role.ADMIN)) {
            String comments = request.getParameter(PARAMETER_REPORT_COMMENTS);
            LocalDate reportDate = LocalDate.parse(request.getParameter(PARAMETER_REPORT_DATE));
            if (reportDate.isAfter(LocalDate.now())) {
                session.setAttribute(INVALID_FORM, INVALID_DATE_MESSAGE + ", date should be less than now");
                return new Router(ADD_REPORT_PAGE, REDIRECT);
            }
            Integer currentWorkId = Integer.valueOf(request.getParameter(CURRENT_WORK_ID));
            var report = new Report(currentWorkId, reportDate, comments);
            if (reportService.insert(report) == null)
                return new Router(WORK_INFO, REDIRECT);

            ReportDTO reportDTO = reportService.findByWorkId(currentWorkId);
            WorkDTO workDTO = workService.findById(currentWorkId);
            session.setAttribute(CURRENT_WORK_REPORT, reportDTO);
            session.setAttribute(CURRENT_WORK, workDTO);


        }
        return new Router(WORK_INFO, REDIRECT);
    }
}
