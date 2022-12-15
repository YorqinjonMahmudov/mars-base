package uz.me.marsbase.controller.command.admin.user;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.USERS;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class DeleteUserCommand implements Command {
    UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        var session = request.getSession();
        if (userService.delete(Integer.parseInt(request.getParameter("deletingUserId")))) {
            var users = userService.getUsers();
            session.setAttribute(USERS, users);
        }
        return new Router(USER_PAGE_FOR_ADMIN, REDIRECT);
    }
}
