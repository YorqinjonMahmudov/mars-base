package uz.me.marsbase.controller.command;


import uz.me.marsbase.controller.command.admin.team.*;
import uz.me.marsbase.controller.command.admin.user.*;
import uz.me.marsbase.controller.command.admin.work.*;
import uz.me.marsbase.controller.command.auth.LogOutCommand;
import uz.me.marsbase.controller.command.auth.SignInFinishCommand;
import uz.me.marsbase.controller.command.auth.SignInGetCommand;
import uz.me.marsbase.controller.command.teamLead.report.AddReportCommand;
import uz.me.marsbase.controller.command.teamLead.report.EditReportCommand;
import uz.me.marsbase.controller.command.teamLead.report.FinishAddReportCommand;
import uz.me.marsbase.controller.command.teamLead.report.FinishEditReportCommand;

public enum CommandType {

    SIGN_IN(new SignInGetCommand()),
    FINISH_SIGN_IN(new SignInFinishCommand()),
    LOG_OUT(new LogOutCommand()),

    HOME(new HomeCommand()),
    USERS_FOR_ADMIN(new UserInfoCommand()),
    ADD_USER(new AddUserCommand()),
    ADD_USER_FINISH(new AddUserFinishCommand()),
    EDIT_USER(new EditUserCommand()),
    FINISH_EDIT_USER(new FinishEditUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    TEAMS(new TeamsInfoCommand()),
    TEAM_MEMBERS_ADMIN(new TeamMembersCommand()),
    ADD_TEAM(new AddTeamCommand()),
    FINISH_ADD_TEAM(new FinishAddTeamCommand()),
    EDIT_TEAM(new EditTeamCommand()),
    FINISH_EDIT_TEAM(new FinishEditTeamCommand()),
    DELETE_TEAM(new DeleteTeamCommand()),
    DELETE_USER_FROM_TEAM(new DeleteUserFromTeamCommand()),
    ADD_USER_TO_TEAM(new FinishAddUserToTeamCommand()),
    WORK_PAGE_FOR_ADMIN(new WorkViewCommand()),
    WORK_INFO_WITH_REPORT(new WorkInfoCommand()),
    ADD_WORK(new AddWorkCommand()),
    FINISH_ADD_WORK(new FinishAddWorkCommand()),
    EDIT_WORK(new EditWorkCommand()),
    FINISH_EDIT_WORK(new FinishEditWorkCommand()),
    DELETE_WORK(new DeleteWorkCommand()),
    ADD_REPORT(new AddReportCommand()),
    FINISH_ADD_REPORT(new FinishAddReportCommand()),
    EDIT_REPORT(new EditReportCommand()),
    FINISH_EDIT_REPORT(new FinishEditReportCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        for (CommandType value : values()) {
            if (value.name().equals(commandStr.toUpperCase()))
                return value.command;
        }

        return HOME.command;
    }

    public static CommandType defineCommandType(String commandStr) {
        for (CommandType value : values()) {
            if (value.name().equals(commandStr.toUpperCase()))
                return value;
        }

        return HOME;
    }


}
