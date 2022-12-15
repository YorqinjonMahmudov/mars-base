package uz.me.marsbase.controller.command.admin.user;

import uz.me.marsbase.controller.command.Command;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.controller.command.navigation.AttributeParameterHolder;
import uz.me.marsbase.controller.command.navigation.PageNavigation;
import uz.me.marsbase.model.dtos.BlockDTO;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.controller.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.validator.AddUserValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static uz.me.marsbase.controller.router.Router.PageChangeType.REDIRECT;

public class FinishEditUserCommand implements Command {
    UserService userService = InstanceHolder.getInstance(UserService.class);
    BlockService blockService = InstanceHolder.getInstance(BlockService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        var session = request.getSession();
        FormValidator validator = new AddUserValidator();
        var parameterMap = request.getParameterMap();
        var validationResult = validator.validate(parameterMap);
        if (validationResult.isEmpty()) {
            var firstName = request.getParameter(AttributeParameterHolder.PARAMETER_USER_FIRSTNAME);
            String lastName = request.getParameter(AttributeParameterHolder.PARAMETER_USER_LASTNAME);
            var email = request.getParameter(AttributeParameterHolder.PARAMETER_EMAIL);
            var password = request.getParameter(AttributeParameterHolder.PARAMETER_USER_PASSWORD);
            Optional<BlockDTO> blockDTO = blockService.findById(Integer.valueOf(request.getParameter(AttributeParameterHolder.PARAMETER_BLOCK_ID)));
            var blockId = blockDTO.get().getId();
            UserDTO editingUser = (UserDTO) session.getAttribute(AttributeParameterHolder.EDITING_USER);
            if (userService.update(editingUser.getId(), new UserDTO(editingUser.getId(), firstName, lastName, email, password, editingUser.getRole(), blockId))) {
                session.setAttribute(AttributeParameterHolder.EDITING_USER, null);
                session.setAttribute(AttributeParameterHolder.USERS, userService.getUsers());
            }
        } else
            session.setAttribute(AttributeParameterHolder.INVALID_FORM, validationResult);

        return new Router(PageNavigation.USER_PAGE_FOR_ADMIN, REDIRECT);
    }
}
