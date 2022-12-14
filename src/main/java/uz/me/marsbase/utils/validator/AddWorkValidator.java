package uz.me.marsbase.utils.validator;


import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;

public class AddWorkValidator implements FormValidator {


    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);
    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);

    @Override
    public Map<String, String> validate(Map<String, String[]> parameters) {

        Map<String, String> validationResult = new HashMap<>();

        /* validate title */
        if (parameters.get(PARAMETER_WORK_TITLE) == null
                || parameters.get(PARAMETER_WORK_TITLE).length == 0
                || parameters.get(PARAMETER_WORK_TITLE)[0].isBlank())
            validationResult.put(PARAMETER_WORK_TITLE, INVALID_WORK_TITLE_MESSAGE);

        /* validate description */
        if (parameters.get(PARAMETER_WORK_DESCRIPTION) == null
                || parameters.get(PARAMETER_WORK_DESCRIPTION).length == 0
                || parameters.get(PARAMETER_WORK_DESCRIPTION)[0].isBlank())
            validationResult.put(PARAMETER_WORK_DESCRIPTION, INVALID_WORK_DESCRIPTION_MESSAGE);

        try {
            /* validate required money */
            if (parameters.get(PARAMETER_WORK_REQUIRED_MONEY) == null
                    || parameters.get(PARAMETER_WORK_REQUIRED_MONEY)[0].isBlank()
                    || Double.parseDouble(parameters.get(PARAMETER_WORK_REQUIRED_MONEY)[0]) < 1)
                validationResult.put(PARAMETER_WORK_REQUIRED_MONEY, INVALID_WORK_REQUIRED_MONEY_MESSAGE);
        } catch (NumberFormatException e) {
            validationResult.put(PARAMETER_WORK_REQUIRED_MONEY, INVALID_WORK_REQUIRED_MONEY_MESSAGE);
        }

        /* validate dates */
        if (parameters.get(PARAMETER_WORK_START_DATE) == null
                || parameters.get(PARAMETER_WORK_START_DATE)[0].isBlank()
                || (!parameters.get(PARAMETER_WORK_FINISH_DATE)[0].isBlank()
                && !parameters.get(PARAMETER_WORK_START_DATE)[0].isBlank()
                && !LocalDate.parse(parameters.get(PARAMETER_WORK_FINISH_DATE)[0]).isAfter(LocalDate.parse(parameters.get(PARAMETER_WORK_START_DATE)[0])))) {
            validationResult.put(PARAMETER_WORK_DATE, INVALID_DATE_MESSAGE);
        }

        /* validate team name */
        if (parameters.get(PARAMETER_TEAM_NAME) == null || teamService.getTeamByName(parameters.get(PARAMETER_TEAM_NAME)[0]) == null)
            validationResult.put(PARAMETER_TEAM_NAME, INVALID_TEAM_NAME_MESSAGE);

        /* validate block name */
        if (parameters.get(PARAMETER_BLOCK_NAME) == null
                || blockService.findByName(parameters.get(PARAMETER_BLOCK_NAME)[0]).isEmpty()
        ) {
            validationResult.put(PARAMETER_BLOCK_NAME, INVALID_BLOCK_NAME);
        }


        return validationResult;
    }
}
