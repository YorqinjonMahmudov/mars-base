package uz.me.marsbase.command.auth;


import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.validator.FormValidator;
import uz.me.marsbase.utils.validator.auth.SignInValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.*;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class SignInFinishCommand implements Command {
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Router execute(HttpServletRequest request) {

        String page = SIGN_IN;
        Router.PageChangeType type = FORWARD;
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();
        FormValidator formValidator = new SignInValidator();
        Map<String, String> validationResult = formValidator.validate(parameterMap);

        if (validationResult.isEmpty()) {

            String username = request.getParameter(PARAMETER_EMAIL);
            String password = request.getParameter(PARAMETER_PASSWORD);

            UserDTO authenticatedUser = userService.isAuthenticated(username, password);

            if (Objects.nonNull(authenticatedUser)) {
                if (authenticatedUser.getRole().equals(Role.ADMIN)) {
                    page = ADMIN_PANEL;
                    type = REDIRECT;
                    session.setAttribute(SESSION_ATTRIBUTE_CURRENT_USER, authenticatedUser);
                } else if (authenticatedUser.getRole().equals(Role.TEAM_LEADER)) {
                    page = TEAM_LEAD_PANEL;
                    type = REDIRECT;
                    session.setAttribute(SESSION_ATTRIBUTE_CURRENT_USER, authenticatedUser);
                }
            } else {
                session.setAttribute(REQ_ATTRIBUTE_USER_INVALID, INVALID_USER_MESSAGE);
            }
        } else {
            session.setAttribute(INVALID_FORM, validationResult);
        }

        return new Router(page, type);
    }
}
