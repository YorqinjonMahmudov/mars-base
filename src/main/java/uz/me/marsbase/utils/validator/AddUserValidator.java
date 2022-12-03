package uz.me.marsbase.utils.validator;


import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.service.BlockService;

import java.util.HashMap;
import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;

public class AddUserValidator implements FormValidator {

    private final PatternValidator validator = InstanceHolder.getInstance(PatternValidator.class);

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);

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

        if (parameters.get(PARAMETER_USER_FIRSTNAME) == null)
            validationResult.put(PARAMETER_USER_FIRSTNAME, INVALID_USER_FIRSTNAME);
        if (parameters.get(PARAMETER_USER_LASTNAME) == null)
            validationResult.put(PARAMETER_USER_LASTNAME, INVALID_USER_LASTNAME);

        if (parameters.get(PARAMETER_BLOCK_NAME) == null || blockService.findByName(parameters.get(PARAMETER_BLOCK_NAME)[0]).isEmpty())
            validationResult.put(PARAMETER_BLOCK_NAME, INVALID_BLOCK_NAME);

        return validationResult;
    }
}
