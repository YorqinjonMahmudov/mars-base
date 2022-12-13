package uz.me.marsbase.command;

import uz.me.marsbase.router.Router;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.PageNavigation.HOME;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

/**
 * Me: yorqinjon
 * Project: mars-base/IntelliJ IDEA
 * Date:Sun 26/11/22 15:19
 */
public class HomeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {

        return new Router(HOME, REDIRECT);
    }
}
