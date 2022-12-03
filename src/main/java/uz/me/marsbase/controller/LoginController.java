//package uz.me.marsbase.controller;
//
//
//import uz.me.marsbase.constants.AppConstants;
//import uz.me.marsbase.model.entity.User;
//import uz.me.marsbase.model.entity.enums.Role;
//import uz.me.marsbase.service.UserService;
//import uz.me.marsbase.service.UserServiceImpl;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet(name = "loginController", urlPatterns = {"/login"})
//public class LoginController extends HttpServlet {
//    private static final UserService userService = new UserServiceImpl();
//
//    @Override
//    public void init() throws ServletException {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//
//        if (!userService.isAuthenticated(email, password)) {
//            req.setAttribute(AppConstants.RESULT, "invalid email or password");
//
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("sign-in.jsp");
//            requestDispatcher.forward(req,resp);
//        } else {
//            User user = userService.getUserByEmail(email);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin-panel.jsp");
//
//            HttpSession session = req.getSession();
//            session.setAttribute(AppConstants.CURRENT_USER, user);
//            if (user.getRole().equals(Role.ADMIN)) {
//                req.getRequestDispatcher("admin-panel.jsp").forward(req,resp);
//            }
//            else if (user.getRole().equals(Role.TEAM_LEADER)) {
//                req.getRequestDispatcher("sign-in.jsp").forward(req,resp);
//            } else
//                resp.sendRedirect("home.jsp");
//
//        }
//    }
//}
