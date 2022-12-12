package uz.me.marsbase.command.admin.work;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.payload.WorkDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class EditWorkCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);
    private final WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(BLOCKS, blockService.getBlocks());

        session.setAttribute(TEAMS, teamService.getTeamDTOs());

        Integer editingWorkId = Integer.valueOf(request.getParameter(EDITING_WORK_ID));
        WorkDTO editingWork = workService.findById(editingWorkId);
        session.setAttribute(EDITING_WORK, session.getAttribute(CURRENT_WORK));

        return new Router(WORK_INFO, REDIRECT);
    }
}
