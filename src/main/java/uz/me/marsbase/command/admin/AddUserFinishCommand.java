package uz.me.marsbase.command.admin;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dto.UserDTO;
import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.validator.AddUserValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.FORWARD;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class AddUserFinishCommand implements Command {

    private final UserService userService = InstanceHolder.getInstance(UserService.class);
    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = USER_PAGE_FOR_ADMIN;
        var type = REDIRECT;
        HttpSession session = request.getSession();
        var parameterMap = request.getParameterMap();

        FormValidator formValidator = new AddUserValidator();
        Map<String, String> validated = formValidator.validate(parameterMap);

        if (validated.isEmpty()) {
            String blockName = request.getParameter(PARAMETER_BLOCK_NAME);
            var optionalBlock = blockService.findByName(blockName);

            var user = User.builder()
                    .blockId(optionalBlock.get().getId())
                    .password(request.getParameter(PARAMETER_PASSWORD))
                    .email(request.getParameter(PARAMETER_EMAIL))
                    .firstName(request.getParameter(PARAMETER_USER_FIRSTNAME))
                    .lastName(request.getParameter(PARAMETER_USER_LASTNAME))
                    .role(Role.USER)
                    .build();
            var hasInserted = userService.insert(user);
            if (!hasInserted) {
                page = ADD_USER_PAGE_FOR_ADMIN;
                type = FORWARD;
            } else {
                List<UserDTO> users = (List<UserDTO>) session.getAttribute(USERS);
                session.setAttribute(USERS, userService.getUsers());
            }

        } else {
            session.setAttribute(REQ_ATTRIBUTE_FORM_INVALID, validated);
        }
        return new Router(page, type);
    }
}
