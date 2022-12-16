package uz.me.marsbase.controller.command.admin.user;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class EditUserCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(BLOCKS, blockService.getBlocks());
        Integer editingUserId = Integer.valueOf(request.getParameter("editingUserId"));
        UserDTO editingUser = userService.getUserById(editingUserId);
        session.setAttribute(EDITING_USER, editingUser);
        session.setAttribute(INVALID_FORM, null);

        return new Router(USER_PAGE_FOR_ADMIN, REDIRECT);
    }
}
