package uz.me.marsbase.utils.validator.auth;


import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.utils.validator.FormValidator;
import uz.me.marsbase.utils.validator.PatternValidator;

import java.util.HashMap;
import java.util.Map;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;

public class SignInValidator implements FormValidator {

    private final PatternValidator validator = InstanceHolder.getInstance(PatternValidator.class);

    @Override
    public Map<String, String> validate(Map<String, String[]> parameters) {

        Map<String, String> validationResult = new HashMap<>();

        if (parameters.get(PARAMETER_EMAIL) == null
                || !validator.validEmail(parameters.get(PARAMETER_EMAIL)[0])
        ) {
            validationResult.put(PARAMETER_EMAIL, INVALID_EMAIL_MESSAGE);
        }

        if (parameters.get(PARAMETER_PASSWORD) == null
                || parameters.get(PARAMETER_PASSWORD).length == 0
                || !validator.validPassword(parameters.get(PARAMETER_PASSWORD)[0])
        ) {
            validationResult.put(PARAMETER_PASSWORD, INVALID_PASSWORD_MESSAGE);
        }

        return validationResult;
    }
}
