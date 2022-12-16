package uz.me.marsbase.controller.filter;

import uz.me.marsbase.controller.command.CommandType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static uz.me.marsbase.controller.command.CommandType.*;
import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.PARAMETER_COMMAND;
import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.PARAMETER_EMAIL;

@WebFilter(urlPatterns = "/controller")
public class EmailFilter implements Filter {
    private List<CommandType> allowedCommands;

    @Override
    public void init(FilterConfig filterConfig) {
        allowedCommands = List.of(
                ADD_USER,
                ADD_USER_FINISH,
                EDIT_USER,
                FINISH_EDIT_USER,
                SIGN_IN,
                FINISH_SIGN_IN,
                USERS_FOR_ADMIN,
                DELETE_USER,
                LOG_OUT
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandStr = request.getParameter(PARAMETER_COMMAND);
        HttpSession session = request.getSession();
        CommandType commandType = defineCommandType(commandStr);

        if (!allowedCommands.contains(commandType))
            session.removeAttribute(PARAMETER_EMAIL);
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
