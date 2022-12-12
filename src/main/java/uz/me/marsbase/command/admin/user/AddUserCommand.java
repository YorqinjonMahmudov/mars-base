package uz.me.marsbase.command.admin.user;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class AddUserCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ADD_USER_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        if (session.getAttribute(BLOCKS) == null)
            session.setAttribute(BLOCKS, blockService.getBlocks());
        if (session.getAttribute(PARAMETER_TEAM_ID) != null) {
            var users = userService.getUsers();
            session.setAttribute(USERS, users);
        }
        return new Router(page, type);
    }
}