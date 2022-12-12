package uz.me.marsbase.command.admin.work;

import uz.me.marsbase.command.Command;
import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.model.entity.enums.Status;
import uz.me.marsbase.payload.BlockDTO;
import uz.me.marsbase.router.Router;
import uz.me.marsbase.service.BlockService;
import uz.me.marsbase.service.TeamService;
import uz.me.marsbase.service.WorkService;
import uz.me.marsbase.utils.validator.AddWorkValidator;
import uz.me.marsbase.utils.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import static uz.me.marsbase.command.navigation.AttributeParameterHolder.*;
import static uz.me.marsbase.command.navigation.PageNavigation.ADD_WORK_PAGE_FOR_ADMIN;
import static uz.me.marsbase.command.navigation.PageNavigation.WORK_INFO;
import static uz.me.marsbase.router.Router.PageChangeType.REDIRECT;

public class FinishEditWorkCommand implements Command {
    TeamService teamService = InstanceHolder.getInstance(TeamService.class);
    BlockService blockService = InstanceHolder.getInstance(BlockService.class);
    WorkService workService = InstanceHolder.getInstance(WorkService.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String page = WORK_INFO;
        HttpSession session = request.getSession();
        FormValidator validator = new AddWorkValidator();
        var parameterMap = request.getParameterMap();
        var validationResult = validator.validate(parameterMap);
        if (validationResult.isEmpty()) {
            String title = request.getParameter(PARAMETER_WORK_TITLE);
            String description = request.getParameter(PARAMETER_WORK_DESCRIPTION);
            double requiredMoney = Double.parseDouble(request.getParameter(PARAMETER_WORK_REQUIRED_MONEY));
            LocalDate startDate = LocalDate.parse(request.getParameter(PARAMETER_WORK_START_DATE));
            LocalDate finishDate = LocalDate.parse(request.getParameter(PARAMETER_WORK_FINISH_DATE));
            int star = Integer.parseInt(request.getParameter("star"));
            Status status = Status.valueOf(request.getParameter("status"));
            String teamName = request.getParameter(PARAMETER_TEAM_NAME);
            String blockName = request.getParameter(PARAMETER_BLOCK_NAME);


            Optional<BlockDTO> blockDTO = blockService.findByName(blockName);
            Integer blockId = blockDTO.get().getId();
            Team teamByName = teamService.getTeamByName(teamName);

            var editingWorkId = Integer.parseInt(request.getParameter(EDITING_WORK_ID));
            Work work = new Work(title, description, requiredMoney, status, startDate, finishDate,
                    star, teamByName.getId(), blockId);
            work.setId(editingWorkId);

            boolean update = workService.update(editingWorkId, work);
            if (update) {
                session.setAttribute(EDITING_WORK, null);
                session.setAttribute(CURRENT_WORK, workService.findById(editingWorkId));
            } else
                page = WORK_INFO;

        } else
            session.setAttribute(INVALID_FORM, validationResult);

        return new Router(page, REDIRECT);
    }
}
