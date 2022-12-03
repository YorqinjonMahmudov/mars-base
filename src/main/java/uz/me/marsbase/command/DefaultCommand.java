package uz.me.marsbase.command;

import uz.me.marsbase.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static uz.me.marsbase.command.navigation.PageNavigation.DEFAULT;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;


public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = DEFAULT;


        return new Router(page, FORWARD);
    }
}
