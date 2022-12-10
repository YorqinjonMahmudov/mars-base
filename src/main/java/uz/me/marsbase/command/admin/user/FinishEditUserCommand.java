package uz.me.marsbase.command.admin.user;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.payload.BlockDTO;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.UserService;
import uz.me.marsbase.utils.validator.AddUserValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.USER_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

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
            var firstName = request.getParameter("firstName");
            var lastName = request.getParameter("lastName");
            var email = request.getParameter("email");
            var password = request.getParameter("password");
            Optional<BlockDTO> blockDTO = blockService.findByName(request.getParameter("blockName"));
            var blockId = blockDTO.get().getId();
            UserDTO editingUser = (UserDTO) session.getAttribute("editingUser");
            if (userService.update(editingUser.getId(), new UserDTO(editingUser.getId(), firstName, lastName, email, password, editingUser.getRole(), blockId))) {
                session.setAttribute(EDITING_USER, null);
                session.setAttribute(USERS,userService.getUsers());
            }
        } else
            session.setAttribute(INVALID_FORM, validationResult);

        return new Router(USER_PAGE_FOR_ADMIN, REDIRECT);
    }
}
