package uz.me.marsbase.controller.command.admin.team;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class AddTeamCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = PageNavigation.ADD_TEAM_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeParameterHolder.USERS) == null)
            session.setAttribute(AttributeParameterHolder.USERS, userService.getUsers());
        return new Router(page, type);
    }
}
