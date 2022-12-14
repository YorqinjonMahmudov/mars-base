package uz.me.marsbase.command.admin.work;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.model.entity.enums.Status;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.WorkService;
import uz.me.marsbase.utils.validator.AddWorkValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Objects;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class FinishAddWorkCommand implements Command {

    private final BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    private final WorkService workService = InstanceHolder.getInstance(WorkService.class);
    private final TeamService teamService = InstanceHolder.getInstance(TeamService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = WORK_PAGE_FOR_ADMIN;
        HttpSession session = request.getSession();
        FormValidator addWorkValidator = new AddWorkValidator();
        var parameterMap = request.getParameterMap();
        var validatedResult = addWorkValidator.validate(parameterMap);
        if (validatedResult.isEmpty()) {
            String teamName = request.getParameter(PARAMETER_TEAM_NAME);
            String blockName = request.getParameter(PARAMETER_BLOCK_NAME);
            String title = request.getParameter(PARAMETER_WORK_TITLE);
            String description = request.getParameter(PARAMETER_WORK_DESCRIPTION);
            double requiredMoney = Double.parseDouble(request.getParameter(PARAMETER_WORK_REQUIRED_MONEY));
            LocalDate startDate = LocalDate.parse(request.getParameter(PARAMETER_WORK_START_DATE));
            LocalDate finishDate = null;
            if (!request.getParameter(PARAMETER_WORK_FINISH_DATE).isBlank())
                finishDate = LocalDate.parse(request.getParameter(PARAMETER_WORK_FINISH_DATE));

            Work work = Work.builder()
                    .title(title)
                    .description(description)
                    .requiredMoney(requiredMoney)
                    .startDate(startDate)
                    .finishDate(finishDate)
                    .blockId(blockService.findByName(blockName).get().getId())
                    .teamId(teamService.getTeamByName(teamName).getId())
                    .status(Status.NEW)
                    .build();
            if (workService.insert(work))
                session.setAttribute(WORK_VIEWS, workService.getWorks());
        } else {
            session.setAttribute(INVALID_FORM,validatedResult);
            page = ADD_WORK_PAGE_FOR_ADMIN;
        }
        return new Router(page, REDIRECT);
    }
}
