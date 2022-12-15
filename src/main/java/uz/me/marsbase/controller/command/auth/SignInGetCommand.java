package uz.me.marsbase.controller.command.auth;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.router.Router;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.controller.command.navigation.PageNavigation.SIGN_IN;
import static uz.me.marsbase.controller.router.Router.PageChangeType.FORWARD;


public class SignInGetCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(SIGN_IN, FORWARD);
    }
}
