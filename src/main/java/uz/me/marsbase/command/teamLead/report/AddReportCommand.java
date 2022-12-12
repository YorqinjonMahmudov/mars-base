package uz.me.marsbase.command.teamLead.report;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_REPORT_PAGE;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class AddReportCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        if (request.getParameter(CURRENT_WORK_ID) != null) {
            return new Router(ADD_REPORT_PAGE, REDIRECT);
        }
        return new Router(WORK_INFO, REDIRECT);
    }
}
