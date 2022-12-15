package uz.me.marsbase.controller.command.admin.work;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.model.dtos.WorkDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class EditWorkCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);
    private final WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeParameterHolder.BLOCKS, blockService.getBlocks());

        session.setAttribute(AttributeParameterHolder.TEAMS, teamService.getTeamDTOs());

        Integer editingWorkId = Integer.valueOf(request.getParameter(AttributeParameterHolder.EDITING_WORK_ID));
        WorkDTO editingWork = workService.findById(editingWorkId);
        session.setAttribute(AttributeParameterHolder.EDITING_WORK, session.getAttribute(AttributeParameterHolder.CURRENT_WORK));

        return new Router(WORK_INFO, REDIRECT);
    }
}
