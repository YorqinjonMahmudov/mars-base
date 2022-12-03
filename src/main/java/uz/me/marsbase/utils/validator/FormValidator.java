package uz.me.marsbase.utils.validator;

import java.util.Map;

public interface FormValidator {
    Map<String, String> validate(Map<String, String[]> parameters);
}
