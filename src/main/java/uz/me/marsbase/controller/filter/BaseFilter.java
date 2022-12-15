package uz.me.marsbase.controller.filter;

import uz.me.marsbase.controller.command.CommandType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static uz.me.marsbase.controller.command.CommandType.*;
import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.CURRENT_USER;
import static uz.me.marsbase.controller.command.navigation.AttributeParameterHolder.PARAMETER_COMMAND;
import static uz.me.marsbase.controller.command.navigation.PageNavigation.HOME;

@WebFilter(filterName = "baseFilter", urlPatterns = "/controller")
public class BaseFilter implements Filter {
    private List<CommandType> allowedCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        allowedCommands = List.of(
                CommandType.HOME,
                SIGN_IN,
                FINISH_SIGN_IN,
                LOG_OUT
        );

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandStr = request.getParameter(PARAMETER_COMMAND);
        HttpSession session = request.getSession();
        CommandType commandType = defineCommandType(commandStr);

        if (!allowedCommands.contains(commandType) && session.getAttribute(CURRENT_USER) == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(HOME);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
