package uz.me.marsbase.command;


import uz.me.marsbase.router.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface
 */
public interface Command {
    Router execute(HttpServletRequest request);
}
