package uz.me.marsbase.command.admin.user;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dto.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.BLOCKS;
import static uz.me.marsbase.command.navigation.AttributeParameterHolder.EDITING_USER;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class EditUserCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = USER_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        var blocks = blockService.getBlocks();
        session.setAttribute(BLOCKS, blocks);
        var parameterMap = request.getParameterMap();
        Integer editingUserId = Integer.valueOf(request.getParameter("editingUserId"));
        var editingUser = userService.getUserById(editingUserId);
        session.setAttribute(EDITING_USER,editingUser);
        return new Router(page, type);
    }
}
