package uz.me.marsbase.controller.command.auth;


import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.controller.command.navigation.PageNavigation.HOME;
import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new Router(HOME, REDIRECT);
    }
}
