package uz.me.marsbase.command.admin.work;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.CURRENT_WORK;
import static uz.me.marsbase.command.navigation.AttributeParameterHolder.TEAMS;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class DeleteWorkCommand implements Command {
    WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = WORK_INFO;
        var session = request.getSession();
        if (request.getParameter("deletingWorkId").isBlank()) {
            return new Router (page,REDIRECT);
        }
        var deletingWorkId = Integer.parseInt(request.getParameter("deletingWorkId"));
        if (workService.delete(deletingWorkId)) {
            var workDTO = workService.findById(deletingWorkId);
            session.setAttribute(CURRENT_WORK, workDTO);
        }
        return new Router(page, REDIRECT);
    }
}
