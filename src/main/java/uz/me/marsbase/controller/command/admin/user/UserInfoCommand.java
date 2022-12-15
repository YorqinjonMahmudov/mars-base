package uz.me.marsbase.controller.command.admin.user;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class UserInfoCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute(EDITING_USER) == null) {
            session.setAttribute(INVALID_FORM, null);
        }
        if (session.getAttribute(EDITING_USER) != null)
            session.setAttribute(EDITING_USER, null);

        List<UserDTO> users = userService.getUsers();

        session.setAttribute(USERS, users);
        return new Router(PageNavigation.USER_PAGE_FOR_ADMIN, REDIRECT);
    }
}
