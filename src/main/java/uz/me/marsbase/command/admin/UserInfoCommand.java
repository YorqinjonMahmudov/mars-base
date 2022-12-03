package uz.me.marsbase.command.admin;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class UserInfoCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = USER_PAGE_FOR_ADMIN;
        HttpSession session = request.getSession();
        var users = userService.getUsers();
        session.setAttribute(AttributeParameterHolder.USERS, users);
            return new Router(page, REDIRECT);
    }
}
