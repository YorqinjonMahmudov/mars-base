package uz.me.marsbase.command.navigation;

/**
 * {@link AttributeParameterHolder} => final class for storing all attributes and parameters
 */
public final class AttributeParameterHolder {

    /**
     * command => command main request param for command which client requests
     */
    public static final String PARAMETER_COMMAND = "command";

    public static final String BLOCKS = "blocks";
    public static final String TEAMS = "teams";
    public static final String WORK_VIEWS = "workViews";
    public static final String CURRENT_WORK = "currentWork";
    public static final String CURRENT_WORK_ID = "currentWorkId";
    public static final String CURRENT_WORK_REPORT = "currentWorkReport";
    public static final String CURRENT_WORK_REPORT_ID = "currentWorkReportId";
    public static final String EDITING_USER = "editingUser";
    public static final String EDITING_TEAM = "editingTeam";
    public static final String EDITING_WORK = "editingWork";
    public static final String EDITING_WORK_ID = "editingWorkId";
    public static final String EDITING_REPORT_ID = "editingReportId";
    public static final String EDITING_REPORT = "editingReport";


    /**
     * SESSION_ATTRIBUTE_EDITING => session attribute for set as session attribute for knowing which is editing
     */
    public static final String SESSION_ATTRIBUTE_EDITING = "editing";
    public static final String CURRENT_USER = "currentUser";



    /**
     * params of user
     */
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";


    /**
     * params of work
     */
    public static final String PARAMETER_WORK_TITLE = "title";
    public static final String PARAMETER_WORK_DESCRIPTION = "description";
    public static final String PARAMETER_WORK_REQUIRED_MONEY = "requiredMoney";
    public static final String PARAMETER_WORK_START_DATE = "startDate";
    public static final String PARAMETER_WORK_FINISH_DATE = "finishDate";
    public static final String PARAMETER_WORK_DATE = "date";

    /**
     * params of work
     */
    public static final String PARAMETER_REPORT_COMMENTS = "comments";
    public static final String PARAMETER_REPORT_DATE = "reportedDate";


    /**
     * params of team
     */
    public static final String PARAMETER_TEAM_NAME = "teamName";
    public static final String PARAMETER_TEAM_LEAD_ID = "teamLeadId";
    public static final String PARAMETER_TEAM_LEAD_EMAIL = "teamLeadEmail";
    public static final String INVALID_TEAM_NAME = "invalid team name";
    public static final String INVALID_TEAM_LEAD_ID = "invalid  TEAM_LEAD_ID";

    /**
     * params of user
     */
    public static final String PARAMETER_USER_FIRSTNAME = "firstName";
    public static final String PARAMETER_USER_LASTNAME = "lastName";
    public static final String PARAMETER_USER_EMAIL = "email";
    public static final String PARAMETER_USER_PASSWORD = "password";
    public static final String PARAMETER_USER_ID = "userId";
    public static final String INVALID_USER_FIRSTNAME_MESSAGE = "invalid user first name";
    public static final String INVALID_USER_LASTNAME_MESSAGE = "invalid user last name";

    /**
     * params of user
     */
    public static final String PARAMETER_BLOCK_NAME = "blockName";
    public static final String PARAMETER_BLOCK_ID = "blockId";
    public static final String INVALID_BLOCK_NAME = "invalid block name";


    /**
     * REQ_ATTRIBUTE_FORM_INVALID => invalid form request param
     */
    public static final String INVALID_FORM = "invalid_form";
    public static final String REQ_ATTRIBUTE_USER_INVALID = "invalid_user";
    public static final String INVALID_USER_MESSAGE = "user not found";
    public static final String INVALID_EMAIL_MESSAGE = "email is not valid";
    public static final String INVALID_PASSWORD_MESSAGE = "Password is not valid";
    public static final String INVALID_TEAM_NAME_MESSAGE = "invalid team name";
    public static final String INVALID_TEAM_LEAD_EMAIL_MESSAGE = "invalidDeleting";
    public static final String INVALID_WORK_TITLE_MESSAGE = "invalid title";
    public static final String INVALID_WORK_DESCRIPTION_MESSAGE = "invalid description";
    public static final String INVALID_WORK_REQUIRED_MONEY_MESSAGE = "invalid required money";
    public static final String INVALID_DATE_MESSAGE = "invalid date";
    public static final String USERS = "users";
    public static final String PARAMETER_TEAM_ID = "teamId";
}
