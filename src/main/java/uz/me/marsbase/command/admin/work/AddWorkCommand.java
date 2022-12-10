package uz.me.marsbase.command.admin.work;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.BLOCKS;
import static uz.me.marsbase.command.navigation.AttributeParameterHolder.TEAMS;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class AddWorkCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ADD_WORK_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        session.setAttribute(TEAMS, teamService.getTeamDTOs());
        session.setAttribute(BLOCKS, blockService.getBlocks());
        return new Router(page, type);
    }
}
