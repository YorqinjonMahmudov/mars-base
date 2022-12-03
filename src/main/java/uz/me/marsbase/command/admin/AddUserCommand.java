package uz.me.marsbase.command.admin;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.BLOCKS;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class AddUserCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ADD_USER_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        var blocks = blockService.getBlocks();
        session.setAttribute(BLOCKS, blocks);
        return new Router(page, type);
    }
}
