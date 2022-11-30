package uz.me.marsbase.controller;


import uz.me.marsbase.dao.UserDAO;
import uz.me.marsbase.dao.imp.UserDAOImpl;
import uz.me.marsbase.entity.User;
import uz.me.marsbase.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/addUser", "/get/{email}", "/get-all-users", "/edit-user/{id}", "/delete-user/{id}"})
public class UserController extends HttpServlet {

    private static final UserDAO userDao = UserDAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("user-info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("/get-all-users")) {
            doGet(req, resp);
            return;
        }

        User user = User.builder()
                .email(req.getParameter("email"))
                .firstName(req.getParameter("firstname"))
                .lastName(req.getParameter("lastname"))
                .role(Role.USER)
                .password(req.getParameter("password"))
                .blockId(Integer.valueOf(req.getParameter("blockId")))
                .build();

        if (userDao.insert(user))
            req.getRequestDispatcher("/get-all-users").forward(req, resp);
        else
            resp.sendRedirect("add-user.jsp");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        System.out.println(req.getMethod());
        System.out.println();
    }
}
