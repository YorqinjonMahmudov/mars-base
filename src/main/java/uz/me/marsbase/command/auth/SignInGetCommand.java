package uz.me.marsbase.command.auth;


import uz.me.marsbase.command.Command;
import uz.me.marsbase.router.Router;

import javax.servlet.http.HttpServletRequest;

import static uz.me.marsbase.command.navigation.PageNavigation.SIGN_IN;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;


public class SignInGetCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(SIGN_IN, FORWARD);
    }
}
