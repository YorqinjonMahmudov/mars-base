package uz.me.marsbase.command;

import uz.me.marsbase.router.Router;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.PageNavigation.DEFAULT;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;


public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(DEFAULT, FORWARD);
    }
}
