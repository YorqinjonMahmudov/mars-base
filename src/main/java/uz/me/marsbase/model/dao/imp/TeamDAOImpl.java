package uz.me.marsbase.model.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.exception.MyException;
import uz.me.marsbase.model.dao.Dao;
import uz.me.marsbase.model.dao.TeamDao;
import uz.me.marsbase.model.dao.UserDao;
import uz.me.marsbase.model.entity.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamDAOImpl implements TeamDao {
    private static final String INSERT = "INSERT INTO team(name, team_lead_id, is_active) VALUES (?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, name, team_lead_id, is_active FROM team WHERE id = ?;";
    private static final String FIND_ALL = "SELECT id, name, team_lead_id, is_active FROM team;";
    private static final String FIND_ALL_BY_TEAM_LEAD_ID = "SELECT id, name, team_lead_id, is_active FROM team WHERE team_lead_id = ?;";
    private static final String FIND_TEAM_LEAD_BY_ID = "SELECT id, name, team_lead_id, is_active FROM team WHERE team_lead_id = ?;";
    private static final String FIND_BY_NAME = "SELECT id, name, team_lead_id, is_active FROM team WHERE name = ?;";
    private static final String DELETE = "UPDATE team SET is_active = false WHERE id = ?;";
    private static final String UPDATE = "UPDATE team SET name = ?, team_lead_id = ?, is_active = ? WHERE id = ?;";
    private final UserDao userDao = UserDaoImpl.getInstance();
    private static TeamDAOImpl teamDaoImpl;

    private TeamDAOImpl() {
    }

    public static TeamDAOImpl getInstance() {
        if (teamDaoImpl == null)
            teamDaoImpl = new TeamDAOImpl();
        return teamDaoImpl;
    }

    @Override
    public boolean insert(Team team) {
        if (findByName(team.getName()).isPresent())
            throw new MyException("team already exists");
        var executeUpdate = executeUpdate(INSERT,
                Dao.STRING + team.getName(),
                Dao.INTEGER + team.getTeamLeadId(),
                Dao.BOOLEAN + team.isActive()
        );
        return executeUpdate;
    }

    @Override
    public boolean delete(int id) {
        return executeUpdate(DELETE,
                Dao.INTEGER + id);
    }

    @Override
    public Optional<Team> findById(int id) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet rs = putArgs(ps, Dao.INTEGER + id).executeQuery();
            Team team;
            if (rs.next() && (team = mapRsToTeam(rs)).getId() != null)
                return Optional.of(team);
            return Optional.empty();
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public List<Team> findAll() {

        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(FIND_ALL);
            List<Team> list = new ArrayList<>();

            while (resultSet.next())
                list.add(mapRsToTeam(resultSet));

            return list;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }

    }

    @Override
    public boolean update(int id, Team team) {
        Optional<Team> optionalTeam = findById(id);
        if (!optionalTeam.isPresent())
            throw new MyException("team not found");
        var byName = findByName(team.getName());
        if (byName.isPresent() && !byName.get().getId().equals(id))
            throw new MyException("this name is already available");

        if (userDao.findById(team.getTeamLeadId()).isEmpty())
            throw new MyException("team lead not found");

        return executeUpdate(UPDATE,
                Dao.STRING + team.getName(),
                Dao.INTEGER + team.getTeamLeadId(),
                Dao.BOOLEAN+team.isActive(),
                Dao.INTEGER + id);
    }

    @Override
    public Optional<Team> findByName(String name) {

        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_NAME)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.STRING + name);
            Team team = null;
            if (resultSet.next())
                team = mapRsToTeam(resultSet);

            if (team == null || team.getId() == null)
                return Optional.empty();
            return Optional.of(team);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Team> findByTeamLeadId(Integer teamLeadId) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_TEAM_LEAD_ID)) {
            ResultSet resultSet = executePrepareStatement(ps,
                    Dao.INTEGER + teamLeadId);
            List<Team> list = new ArrayList<>();

            while (resultSet.next())
                list.add(mapRsToTeam(resultSet));

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement putArgs(PreparedStatement ps, String... args) throws SQLException {
        return Dao.setPSArgs(ps, args);
    }

    private ResultSet executePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args).executeQuery();
    }


    /**
     * Executes Query to update.
     *
     * @param ps   PreparedStatement Query.
     * @param args PreparedStatement Args.
     * @return true If any row updated, otherwise false
     * @throws MyException if any error occurs;
     */
    private boolean executeUpdate(String ps, String... args) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(ps)) {
            var executeUpdate = putArgs(pStatement, args)
                    .executeUpdate();
            return executeUpdate > 0;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    /**
     * Converts ResultSet data To Whole Object.
     *
     * @param rs Result of executed Query.
     * @return Team object if ResultSet is blank then return Team without id.
     */
    private Team mapRsToTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setTeamLeadId(rs.getInt("team_lead_id"));
        team.setName(rs.getString("name"));
        team.setActive(rs.getBoolean("is_active"));
        return team;
    }

    private boolean findTeamLeadById(Integer teamLeadId) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_TEAM_LEAD_BY_ID)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.INTEGER + teamLeadId);
            if (resultSet.next())
                return resultSet.getInt("id") != 0;
            return false;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }
}
