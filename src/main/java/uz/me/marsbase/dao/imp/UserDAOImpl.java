package uz.me.marsbase.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.dao.Dao;
import uz.me.marsbase.dao.UserDAO;
import uz.me.marsbase.entity.User;
import uz.me.marsbase.entity.enums.Role;
import uz.me.marsbase.exception.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final String INSERT = "INSERT INTO users (email, role,first_name, last_name, password, block_id) VALUES(?,?,?,?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, role, email, first_name, last_name, password, block_id FROM users WHERE id = ?;";
    private static final String FIND_BY_EMAIL = "SELECT id, role, email,first_name, last_name, password, block_id FROM users WHERE email = ?;";
    private static final String FIND_ALL = "SELECT id, role, email, first_name, last_name, password, block_id FROM users;";
    private static final String MAKE_TEAM_LEAD = "UPDATE users SET role = ? WHERE id = ?;";
    private static final String CHANGE_FROM_TEAM_LEAD_TO_USER = "UPDATE users SET role = ? WHERE id = ?;";
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET email =?, first_name =?, last_name = ?, password = ?, block_id = ? WHERE id = ?;";
    private static UserDAOImpl userDaoImpl;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        if (userDaoImpl == null)
            userDaoImpl = new UserDAOImpl();
        return userDaoImpl;
    }

    @Override
    public boolean insert(User user) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(INSERT)) {
            return executeUpdatePrepareStatement(ps,
                    Dao.STRING + user.getEmail(),
                    Dao.STRING + user.getRole().name(),
                    Dao.STRING + user.getFirstName(),
                    Dao.STRING + user.getLastName(),
                    Dao.STRING + user.getPassword(),
                    Dao.INTEGER + user.getBlockId());
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        throw new MyException("User can not be deleted");
    }

    @Override
    public Optional<User> findById(int id) {
        try (PreparedStatement ps = MyConnectionPool
                .getInstance()
                .getConnection()
                .prepareStatement(FIND_BY_ID)) {
            return getOptionalUser(executePrepareStatement(ps, Dao.INTEGER + id));
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {

        try {
            Connection connection = MyConnectionPool.getInstance().getConnection();
            List<User> list = new ArrayList<>();

            PreparedStatement ps = null;
            ps = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = executePrepareStatement(ps);

            while (resultSet.next()) {
                list.add(getUserFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(int id, User user) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection()) {
            return checkAndUpdateUser(connection, id, user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePassword(int id, String password) {
        try (PreparedStatement ps = MyConnectionPool
                .getInstance()
                .getConnection()
                .prepareStatement(CHANGE_PASSWORD)) {
            return executeUpdatePrepareStatement(ps, Dao.INTEGER + id, Dao.STRING + password);
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean makeTeamLead(int userId) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(MAKE_TEAM_LEAD)) {
            return executeUpdatePrepareStatement(ps, Dao.INTEGER + userId, Dao.STRING + Role.TEAM_LEADER.name());
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean changeFromTeamLeadToUser(int userId) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(CHANGE_FROM_TEAM_LEAD_TO_USER)) {
            return executeUpdatePrepareStatement(ps, Dao.INTEGER + userId, Dao.STRING + Role.USER.name());
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(FIND_BY_EMAIL)) {
            return getOptionalUser(executePrepareStatement(ps, Dao.STRING + email));
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean isAuthenticated(String email, String password) {
        Optional<User> optionalUser = findByEmail(email);

        return optionalUser.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    private ResultSet executePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args).executeQuery();
    }

    private boolean executeUpdatePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args).executeUpdate() == 1;
    }

    private PreparedStatement putArgs(PreparedStatement ps, String... args) throws SQLException {
        return Dao.setPSArgs(ps, args);
    }

    private Optional<User> getOptionalUser(ResultSet rs) {
        try {
            if (rs.next()) {
                User user;
                user = getUserFromResultSet(rs);
                if (user.getId() != null)
                    return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return Optional.empty();
    }

    public User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setBlockId(rs.getInt("block_id"));
        return user;
    }

    private boolean checkAndUpdateUser(Connection connection, int id, User user) throws SQLException {
        if (!existById(connection, id))
            return false;

//        todo check exist block

        PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
        return executeUpdatePrepareStatement(ps,
                Dao.STRING + user.getEmail(),
                Dao.STRING + user.getFirstName(),
                Dao.STRING + user.getLastName(),
                Dao.STRING + user.getPassword(),
                Dao.INTEGER + user.getBlockId());
    }

    private boolean existById(Connection connection, int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
        ResultSet resultSet = executePrepareStatement(ps, Dao.INTEGER + id);

        if (resultSet.next())
            return resultSet.getInt("id") == 0;

        return false;
    }

}
