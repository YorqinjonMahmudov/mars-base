package uz.me.marsbase.controller;


import uz.me.marsbase.ConnectionSource;
import uz.me.marsbase.entity.Block;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/add-block", "/edit-block", "/delete-block{id}","/get-all-blocks"})
public class BlockController extends HttpServlet {

    ConnectionSource connectionSource = new ConnectionSource();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Block block = Block.builder()
                .name(req.getParameter("name"))
                .area(Double.parseDouble(req.getParameter("area")))
                .location(req.getParameter("location"))
                .build();

        String query = "INSERT INTO block (name, area, location)  VALUES(" +
                " '" + block.getName()
                + "', " + block.getArea()
                + ", '" + block.getLocation()
                + "'); ";

        try (Connection connection = connectionSource.createConnection()) {

            connection.createStatement().execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("add-user.jsp");

    }
}
