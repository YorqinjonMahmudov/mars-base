package uz.me.marsbase.command.teamLead.report;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.payload.ReportDTO;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class FinishEditReportCommand implements Command {

    private final ReportService reportService = InstanceHolder.getInstance(ReportService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO currentUser = (UserDTO) session.getAttribute(CURRENT_USER);

        if (currentUser.getRole().equals(Role.TEAM_LEADER) || currentUser.getRole().equals(Role.ADMIN)) {
            String comments = request.getParameter(PARAMETER_REPORT_COMMENTS);
            LocalDate reportDate = LocalDate.parse(request.getParameter(PARAMETER_REPORT_DATE));
            if (reportDate.isAfter(LocalDate.now())) {
                session.setAttribute(INVALID_FORM, INVALID_DATE_MESSAGE + ", date should be less than now");
                return new Router(WORK_INFO, FORWARD);
            }

            session.setAttribute(INVALID_FORM, null);
            Integer editingWorkId = Integer.valueOf(request.getParameter(EDITING_WORK_ID));
            Integer editingReportId = Integer.valueOf(request.getParameter(EDITING_REPORT_ID));
            var report = new Report(editingWorkId, reportDate, comments);
            report.setId(editingReportId);
            if (!reportService.update(editingReportId, report))
                return new Router(WORK_INFO, FORWARD);

            ReportDTO reportDTO = reportService.findByWorkId(editingWorkId);
            session.setAttribute(CURRENT_WORK_REPORT, reportDTO);
            session.setAttribute(EDITING_REPORT, null);

        }
        return new Router(WORK_INFO, REDIRECT);
    }
}
