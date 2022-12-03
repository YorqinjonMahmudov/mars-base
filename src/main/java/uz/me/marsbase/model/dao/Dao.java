package uz.me.marsbase.model.dao;

import uz.me.marsbase.model.entity.abs.AbsEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends AbsEntity> {

    String INTEGER = "Integer - ";
    String STRING = "String - ";
    String DOUBLE = "Double - ";
    String TIMESTAMP = "Timestamp - ";

    String BOOLEAN = "Boolean - ";


    String DATE = "0000-00-00";

    static PreparedStatement setPSArgs(PreparedStatement ps, String... args) throws SQLException {
        int index = 0;
        for (String arg : args) {
            if (arg.startsWith(INTEGER)) ps.setInt(++index, Integer.parseInt(arg.substring(10)));
            else if (arg.startsWith(STRING)) ps.setString(++index, arg.substring(9));
            else if (arg.startsWith(DOUBLE)) ps.setDouble(++index, Double.parseDouble(arg.substring(9)));
            else if (arg.startsWith(BOOLEAN)) ps.setBoolean(++index, Boolean.parseBoolean(arg.substring(10)));
            else if (arg.startsWith(TIMESTAMP))
                ps.setTimestamp(++index, Timestamp.valueOf(arg.substring(TIMESTAMP.length(), TIMESTAMP.length() + DATE.length()) + " " + arg.substring(TIMESTAMP.length() + DATE.length() + 1)));
        }
        return ps;
    }

    boolean insert(T t);

    boolean delete(int id);

    Optional<T> findById(int id);

    List<T> findAll();

    boolean update(int id, T t);
}
