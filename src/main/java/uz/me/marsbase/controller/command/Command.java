package uz.me.marsbase.controller.command;


import uz.me.marsbase.controller.router.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface
 */
public interface Command {
    Router execute(HttpServletRequest request);
}
