package uz.me.marsbase.command.instanceHolder;


import uz.me.marsbase.model.dao.*;
import uz.me.marsbase.model.dao.imp.*;
import uz.me.marsbase.service.*;
import uz.me.marsbase.utils.encoder.PasswordEncoder;
import uz.me.marsbase.utils.validator.PatternValidator;

public class InstanceHolder {
    private static final PasswordEncoder passwordEncoder = new PasswordEncoder();
    private static final BlockDao blockDao = BlockDAOImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final WorkDao workDao = WorkDAOImpl.getInstance();
    private static final ReportDao reportDao = ReportDAOImpl.getInstance();
    private static final TeamDao teamDao = TeamDAOImpl.getInstance();

    private static final UserService userService = new UserServiceImpl();
    private static final BlockService blockService = new BlockServiceImpl();
    private static final TeamService teamService = new  TeamServiceImpl();

    private static final PatternValidator patternValidator = new PatternValidator();

    public static <T> T getInstance(Class<?> clazz) {
        return switch (clazz.getSimpleName()) {
            case "PatternValidator" -> (T) patternValidator;
            case "PasswordEncoder" -> (T) passwordEncoder;
            case "UserService" -> (T) userService;
            case "BlockService" -> (T) blockService;
            case "TeamService" -> (T) teamService;
            case "ReportDao" -> (T) reportDao;
            case "TeamDao" -> (T) teamDao;
            case "WorkDao" -> (T) workDao;
            case "UserDao" -> (T) userDao;
            case "BlockDao" -> (T) blockDao;
            default -> null;
        };
    }
}