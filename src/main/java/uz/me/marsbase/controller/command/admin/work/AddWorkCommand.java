package uz.me.marsbase.controller.command.admin.work;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.PageNavigation.ADD_WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.FORWARD;

public class AddWorkCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeParameterHolder.TEAMS, teamService.getTeamDTOs());
        session.setAttribute(AttributeParameterHolder.BLOCKS, blockService.getBlocks());
        return new Router(ADD_WORK_PAGE_FOR_ADMIN, FORWARD);
    }
}
