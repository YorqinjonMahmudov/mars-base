package uz.me.marsbase.controller.command;

import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.controller.router.Router;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

/**
 * Me: yorqinjon
 * Project: mars-base/IntelliJ IDEA
 * Date:Sun 26/11/22 15:19
 */
public class HomeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {

        return new Router(PageNavigation.HOME, REDIRECT);
    }
}
