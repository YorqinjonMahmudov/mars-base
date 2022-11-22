package uz.me.marsbase.controller;


import uz.me.marsbase.ConnectionSource;
import uz.me.marsbase.entity.User;
import uz.me.marsbase.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/addUser"})
public class UserController extends HttpServlet {
    Connection connection = ConnectionSource.createConnection();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = User.builder()
                .email(req.getParameter("email"))
                .firstName(req.getParameter("firstname"))
                .lastName(req.getParameter("lastname"))
                .role(Role.USER)
                .password(req.getParameter("password"))
                .birthDate(LocalDate.parse(req.getParameter("birthDate")))
                .blockId(Integer.valueOf(req.getParameter("blockId")))
                .build();

        String query = "INSERT INTO users (email, first_name, last_name, password, birth_date, block_id)  VALUES(" +
                " '" + user.getEmail()
                + "', '" + user.getFirstName()
                + "', '" + user.getLastName()
                + "', '" + user.getPassword()
                + "', '" + user.getBirthDate()
                + "', " + user.getBlockId() + ");";

        try {
            connection.createStatement().execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect("add-user.jsp");

    }
}
