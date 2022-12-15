package uz.me.marsbase.controller.command.admin.user;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.ADD_USER_PAGE;
import static uz.me.marsbase.controller.router.Router.PageChangeType.FORWARD;

public class AddUserCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = ADD_USER_PAGE;
        var type = FORWARD;
        HttpSession session = request.getSession();
        session.setAttribute(BLOCKS, blockService.getBlocks());
        session.setAttribute(INVALID_FORM, null);
        if (session.getAttribute(PARAMETER_TEAM_ID) != null) {
            List<UserDTO> users = userService.getUsers();
            session.setAttribute(USERS, users);
        }
        return new Router(page, type);
    }
}
