package uz.me.marsbase.utils.validator;


import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.INVALID_EMAIL_MESSAGE;
import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.PARAMETER_EMAIL;

public class CheckEmailValidator implements FormValidator {

    private final PatternValidator validator = InstanceHolder.getInstance(PatternValidator.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Map<String, String> validate(Map<String, String[]> parameters) {

        Map<String, String> validationResult = new HashMap<>();
        var email = parameters.get(PARAMETER_EMAIL)[0];
        if (parameters.get(PARAMETER_EMAIL) == null
                || !validator.validEmail(email)
                || userService.getUserByEmail(email) == null
        ) {
            validationResult.put(PARAMETER_EMAIL, INVALID_EMAIL_MESSAGE);
        }

        return validationResult;
    }
}
