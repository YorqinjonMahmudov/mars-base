package uz.me.marsbase.controller.filter;

import uz.me.marsbase.controller.command.CommandType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static uz.me.marsbase.controller.command.CommandType.*;
import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.*;

@WebFilter(urlPatterns = "/controller")
public class DateFilter implements Filter {
    private List<CommandType> allowedCommands;

    @Override
    public void init(FilterConfig filterConfig) {
        allowedCommands = List.of(
                ADD_WORK,
                FINISH_ADD_WORK,
                EDIT_WORK,
                FINISH_EDIT_WORK,
                WORK_INFO_WITH_REPORT,
                DELETE_WORK,
                ADD_REPORT,
                FINISH_ADD_REPORT,
                EDIT_REPORT,
                FINISH_EDIT_REPORT
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandStr = request.getParameter(PARAMETER_COMMAND);
        HttpSession session = request.getSession();
        CommandType commandType = defineCommandType(commandStr);

        if (!allowedCommands.contains(commandType)) {
            session.removeAttribute(PARAMETER_WORK_DATE);
            session.removeAttribute(PARAMETER_REPORT_DATE);
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
