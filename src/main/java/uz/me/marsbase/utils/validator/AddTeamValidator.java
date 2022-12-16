package uz.me.marsbase.utils.validator;


import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;

public class AddTeamValidator implements FormValidator {


    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public Map<String, String> validate(Map<String, String[]> parameters) {

        Map<String, String> validationResult = new HashMap<>();

        if (parameters.get(PARAMETER_TEAM_NAME) == null
                || parameters.get(PARAMETER_TEAM_NAME).length == 0
        )
            validationResult.put(PARAMETER_TEAM_NAME, INVALID_TEAM_NAME_MESSAGE);
        Team teamByName = teamService.getTeamByName(parameters.get(PARAMETER_TEAM_NAME)[0]);
        if (teamByName != null && (Objects.isNull(parameters.get("editingTeamId"))
                || !teamByName.getId().equals(Integer.valueOf(parameters.get("editingTeamId")[0]))))
            validationResult.put(PARAMETER_TEAM_NAME, INVALID_TEAM_NAME_MESSAGE);


        if (parameters.get(PARAMETER_TEAM_LEAD_EMAIL) == null
                || parameters.get(PARAMETER_TEAM_LEAD_EMAIL).length == 0
                || userService.getUserByEmail(parameters.get(PARAMETER_TEAM_LEAD_EMAIL)[0]).getId() == null
        ) {
            validationResult.put(PARAMETER_TEAM_LEAD_EMAIL, INVALID_TEAM_LEAD_EMAIL_MESSAGE);
        }


        return validationResult;
    }
}
