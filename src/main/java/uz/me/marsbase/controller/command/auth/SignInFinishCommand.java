package uz.me.marsbase.controller.command.auth;


import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.admin.team.TeamsInfoCommand;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.encoder.PasswordEncoder;
import uz.me.marsbase.utils.validator.FormValidator;
import uz.me.marsbase.utils.validator.auth.SignInValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.*;
import static uz.me.marsbase.controller.router.Router.PageChangeType.FORWARD;

public class SignInFinishCommand implements Command {
    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final PasswordEncoder passwordEncoder = InstanceHolder.getInstance(PasswordEncoder.class);

    @Override
    public Router execute(HttpServletRequest request) {

        String page = SIGN_IN;
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();
        FormValidator formValidator = new SignInValidator();
        Map<String, String> validationResult = formValidator.validate(parameterMap);

        if (validationResult.isEmpty()) {
            String username = request.getParameter(PARAMETER_EMAIL);
            String password = request.getParameter(PARAMETER_PASSWORD);

            UserDTO authenticatedUser = userService.isAuthenticated(username, passwordEncoder.encode(password));

            if (Objects.nonNull(authenticatedUser)) {
                if (authenticatedUser.getRole().equals(Role.ADMIN)) {
                    page = ADMIN_PANEL;
                    session.setAttribute(CURRENT_USER, authenticatedUser);
                } else if (authenticatedUser.getRole().equals(Role.TEAM_LEADER)) {
                    page = TEAM_LEAD_PANEL;
                    session.setAttribute(CURRENT_USER, authenticatedUser);
                    TeamsInfoCommand teamsInfoCommand = new TeamsInfoCommand();
                    teamsInfoCommand.execute(request);
                } else
                    request.setAttribute(REQ_ATTRIBUTE_USER_INVALID, "you have no permission");
            } else {
                request.setAttribute(REQ_ATTRIBUTE_USER_INVALID, INVALID_USER_MESSAGE);
            }
        } else {
            request.setAttribute(REQ_ATTRIBUTE_USER_INVALID, INVALID_USER_MESSAGE);
        }

        return new Router(page, FORWARD);
    }
}
