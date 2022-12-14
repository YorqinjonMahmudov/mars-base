package uz.me.marsbase.dao.imp;

import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.dao.Dao;
import uz.me.marsbase.dao.UserDao;
import uz.me.marsbase.exception.MyException;
import uz.me.marsbase.mappers.UserMapper;
import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.utils.encoder.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserMapper userMapper = new UserMapper();
    private static final PasswordEncoder passwordEncoder = InstanceHolder.getInstance(PasswordEncoder.class);


    private static final String INSERT = "INSERT INTO users (email, role,first_name, last_name, password, block_id) VALUES(?,?,?,?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, role, email, first_name, last_name, password, block_id FROM users WHERE id = ?;";
    private static final String FIND_BY_EMAIL = "SELECT id, role, email,first_name, last_name, password, block_id FROM users WHERE email = ?;";
    private static final String FIND_ALL = "SELECT id, role, email, first_name, last_name, password, block_id FROM users;";
    private static final String MAKE_TEAM_LEAD = "UPDATE users SET role = ? WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET email =?, first_name =?, last_name = ?, password = ?, block_id = ? WHERE id = ?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static UserDaoImpl userDaoImpl;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (userDaoImpl == null)
            userDaoImpl = new UserDaoImpl();
        return userDaoImpl;
    }

    @Override
    public boolean insert(User user) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            if (findByEmail(user.getEmail()).isPresent())
                return false;

            var encoded = passwordEncoder.encode(user.getPassword());
            return executeUpdatePrepareStatement(ps,
                    Dao.STRING + user.getEmail(),
                    Dao.STRING + user.getRole().name(),
                    Dao.STRING + user.getFirstName(),
                    Dao.STRING + user.getLastName(),
                    Dao.STRING + encoded,
                    Dao.INTEGER + user.getBlockId());
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            putArgs(ps, Dao.INTEGER + id);
            var execute = ps.execute();
            return !execute;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<User> findById(int id) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            return getOptionalUser(executePrepareStatement(ps, Dao.INTEGER + id));
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {
            List<User> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next())
                list.add(getUserFromResultSet(resultSet));

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
    public boolean makeTeamLead(int userId) {
        var optionalUser = findById(userId);
        if (optionalUser.isPresent() && optionalUser.get().getRole().equals(Role.USER)) {
            try (Connection connection = MyConnectionPool.getInstance().getConnection();
                 PreparedStatement ps = connection.prepareStatement(MAKE_TEAM_LEAD)) {
                return executeUpdatePrepareStatement(ps, Dao.STRING + Role.TEAM_LEADER.name(), Dao.INTEGER + userId);
            } catch (SQLException e) {
                throw new MyException(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_EMAIL)) {
            return getOptionalUser(executePrepareStatement(ps, Dao.STRING + email));
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public UserDTO isAuthenticated(String email, String password) {
        Optional<User> optionalUser = findByEmail(email);
        if (optionalUser.isEmpty() || !Objects.equals(optionalUser.get().getPassword(), password)) {
            return null;
        }
        return userMapper.toDto(optionalUser.get());
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

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            var encoded = passwordEncoder.encode(user.getPassword());
            return executeUpdatePrepareStatement(ps,
                    Dao.STRING + user.getEmail(),
                    Dao.STRING + user.getFirstName(),
                    Dao.STRING + user.getLastName(),
                    Dao.STRING + encoded,
                    Dao.INTEGER + user.getBlockId(),
                    Dao.INTEGER + id);
        }
    }

    private boolean existById(Connection connection, int id) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.INTEGER + id);
            if (resultSet.next())
                return resultSet.getInt("id") != 0;
            return false;
        }
    }

}
