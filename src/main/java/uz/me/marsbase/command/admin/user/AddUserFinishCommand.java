package uz.me.marsbase.command.admin.user;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.encoder.PasswordEncoder;
import uz.me.marsbase.utils.validator.AddUserValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_USER_PAGE;
import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class AddUserFinishCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final PasswordEncoder passwordEncoder = InstanceHolder.getInstance(PasswordEncoder.class);


    @Override
    public Router execute(HttpServletRequest request) {
        String page = USER_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();

        FormValidator formValidator = new AddUserValidator();
        Map<String, String> validated = formValidator.validate(parameterMap);

        if (validated.isEmpty()) {
            Integer blockId = Integer.valueOf(request.getParameter(PARAMETER_BLOCK_ID));
            var optionalBlock = blockService.findById(blockId);

            var user = User.builder()
                    .blockId(optionalBlock.get().getId())
                    .password(request.getParameter(PARAMETER_PASSWORD))
                    .email(request.getParameter(PARAMETER_EMAIL))
                    .firstName(request.getParameter(PARAMETER_USER_FIRSTNAME))
                    .lastName(request.getParameter(PARAMETER_USER_LASTNAME))
                    .role(Role.USER)
                    .build();
            boolean hasInserted = userService.insert(user);
            if (!hasInserted) {
                session.setAttribute(INVALID_FORM, "User already exists");
                page = ADD_USER_PAGE;
                type = FORWARD;
            } else {
                List<UserDTO> users = userService.getUsers();
                session.setAttribute(USERS, users);
            }

        } else {
            session.setAttribute(INVALID_FORM, validated);
        }
        return new Router(page, type);
    }
}
