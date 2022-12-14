package uz.me.marsbase.command.admin.user;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.encoder.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.EDITING_USER;
import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class UserInfoCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute(EDITING_USER) != null)
            session.setAttribute(EDITING_USER, null);

        List<UserDTO> users = userService.getUsers();

        session.setAttribute(AttributeParameterHolder.USERS, users);
        return new Router(USER_PAGE_FOR_ADMIN, REDIRECT);
    }
}
