package uz.me.marsbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSource {
    private static final String URL = "jdbc:postgresql://localhost:5432/mars_base";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1223";

    public  Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("error in creating connection", e);
        }
    }
}