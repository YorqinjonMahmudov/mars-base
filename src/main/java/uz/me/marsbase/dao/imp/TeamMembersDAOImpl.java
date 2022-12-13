package uz.me.marsbase.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.dao.Dao;
import uz.me.marsbase.exception.MyException;
import uz.me.marsbase.dao.TeamMembersDao;
import uz.me.marsbase.model.entity.TeamMember;
import uz.me.marsbase.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamMembersDAOImpl implements TeamMembersDao {
    private static TeamMembersDAOImpl teamMemberDao;
    private static final UserDaoImpl userDAO = UserDaoImpl.getInstance();
    private static final String INSERT = "INSERT INTO team_member(team_id, user_id ) VALUES ( ?, ?);";
    private static final String FIND_BY_ID = "SELECT id, team_id, user_id FROM team_member WHERE id=?;";
    private static final String FIND_ALL = "SELECT id, team_id, user_id FROM team_member;";
    private static final String FIND_BY_TEAM_ID_AND_USER_ID = "SELECT id, team_id, user_id FROM team_member where team_id = ? AND user_id = ? LIMIT 1 ;";
    private static final String FIND_ALL_USERS_BY_TEAM_LEAD_ID = "SELECT u.id, u.email, u.first_name, u.last_name, u.role,u.password,u.block_id\nFROM team_member tm\n         JOIN team t on t.id = tm.team_id\n         JOIN users u on u.id = tm.user_id\nWHERE t.team_lead_id =?;";
    private static final String FIND_ALL_USERS_BY_TEAM_ID = "SELECT u.id, u.email, u.first_name, u.last_name, u.role,u.password,u.block_id\nFROM team_member tm\n      JOIN users u on u.id = tm.user_id \nWHERE tm.team_id =?;";
    private static final String DELETE_BY_ID = "DELETE FROM team_member where id=?;";
    private static final String DELETE_USER_FROM_TEAM_BY_ID = "DELETE FROM team_member where user_id=? AND team_id = ?;";


    private TeamMembersDAOImpl() {
    }

    public static TeamMembersDAOImpl getInstance() {
        if (teamMemberDao == null)
            teamMemberDao = new TeamMembersDAOImpl();
        return teamMemberDao;
    }


    @Override
    public boolean insert(TeamMember teamMember) throws MyException {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            if (existByTeamIdAndUserId(teamMember.getTeamId(), teamMember.getUserId()))
                return true;
            var psArgs = Dao.setPSArgs(ps,
                    Dao.INTEGER + teamMember.getTeamId(),
                    Dao.INTEGER + teamMember.getUserId());
            return psArgs
                    .executeUpdate() > 0;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) throws MyException {
        return executeUpdate(DELETE_BY_ID, Dao.INTEGER + id);
    }

    @Override
    public Optional<TeamMember> findById(int id) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet rs = Dao.setPSArgs(ps, Dao.INTEGER + id).executeQuery();
            TeamMember teamMember;
            if (rs.next() && (teamMember = mapRsToTeamMember(rs)).getId() != null)
                return Optional.of(teamMember);
            return Optional.empty();
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public List<TeamMember> findAll() {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            List<TeamMember> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next())
                list.add(mapRsToTeamMember(resultSet));
            return list;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    /**
     * can't update values to do update delete it first and insert again
     */
    @Override
    public boolean update(int id, TeamMember teamMember) {
        return false;
    }

    @Override
    public List<User> findTeamMembersByTeamId(Integer teamId) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_USERS_BY_TEAM_ID)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.INTEGER + teamId);
            return getUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findTeamMembersByTeamLeaderId(Integer teamLeaderId) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_USERS_BY_TEAM_LEAD_ID)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.INTEGER + teamLeaderId);
            return getUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteIUser(int deletingUserId, int teamId) {
        return executeUpdate(DELETE_USER_FROM_TEAM_BY_ID,
                Dao.INTEGER + deletingUserId,
                Dao.INTEGER + teamId);
    }

    boolean existByTeamIdAndUserId(int teamId, int userId) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_TEAM_ID_AND_USER_ID)) {
            var resultSet = executePrepareStatement(ps,
                    Dao.INTEGER + teamId,
                    Dao.INTEGER + userId);
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();

        while (resultSet.next())
            list.add(userDAO.getUserFromResultSet(resultSet));

        return list;
    }

    private ResultSet executePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args).executeQuery();
    }

    private PreparedStatement putArgs(PreparedStatement ps, String... args) throws SQLException {
        return Dao.setPSArgs(ps, args);
    }

    private boolean executeUpdate(String ps, String... args) throws MyException {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(ps)) {
            return putArgs(pStatement, args).executeUpdate() > 0;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    private TeamMember mapRsToTeamMember(ResultSet rs) throws SQLException {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(rs.getInt("id"));
        teamMember.setTeamId(rs.getInt("team_id"));
        teamMember.setUserId(rs.getInt("user_id"));
        return teamMember;
    }

}
