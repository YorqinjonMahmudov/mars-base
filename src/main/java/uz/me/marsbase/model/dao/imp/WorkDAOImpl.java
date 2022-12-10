package uz.me.marsbase.model.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.exception.MyException;
import uz.me.marsbase.model.dao.BlockDao;
import uz.me.marsbase.model.dao.Dao;
import uz.me.marsbase.model.dao.WorkDao;
import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.model.entity.enums.Status;
import uz.me.marsbase.payload.WorkDTO;
import uz.me.marsbase.payload.WorkViewDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkDAOImpl implements WorkDao {

    private static final String INSERT = "INSERT INTO work (title, description, required_money, status, start_date, finish_date, team_id, block_id) VALUES(?,?,?,?,?,?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, title, description, required_money, status, start_date, finish_date, star, team_id, block_id FROM work WHERE id = ?;";
    private static final String FIND_BY_ID_FOR_DTO = "SELECT w.id id, title, description, required_money, start_date, finish_date, star, status, b.name blockName, t.name teamName FROM work w JOIN block b on b.id = w.block_id JOIN team t on t.id = w.team_id WHERE  w.id = ?;";
    private static final String FIND_BY_TITLE = "SELECT id, title, description, required_money, status, start_date, finish_date, star, team_id, block_id FROM work WHERE title = ?;";
    private static final String FIND_ALL = "SELECT id, title, description, required_money, status, start_date, finish_date, star, team_id, block_id FROM work;";
    private static final String FIND_ALL_FOR_VIEW = "SELECT w.id id,  w.title title, t.name teamName, b.name blockName FROM work w JOIN team t on t.id = w.team_id JOIN block b on b.id = w.block_id;";
    private static final String FIND_ALL_BY_STATUS = "SELECT id, title, description, required_money, status, start_date, finish_date, star, team_id, block_id FROM work WHERE status = ?;";
    private static final String FIND_ALL_BY_BLOCK_ID = "SELECT id, title, description, required_money, status, start_date, finish_date, star, team_id, block_id FROM work WHERE blockId = ?';";
    private static final String FIND_ALL_BY_TEAM_ID = "SELECT id, title, description, required_money, status, start_date, finish_date, star, team_id, block_id FROM work WHERE blockId = ?';";
    private static final String SET_STATUS_TO = "UPDATE work SET status = ? WHERE id = ?;";
    private static final String UPDATE_WORK = "UPDATE work SET title =?, description =?, required_money = ?, start_date = ?, finish_date = ?, team_id = ?, block_id = ? WHERE id = ?;";
    private static WorkDAOImpl workDAOImpl;
    public static final BlockDao blockDao = BlockDAOImpl.getInstance();

    public static WorkDAOImpl getInstance() {
        if (workDAOImpl == null)
            workDAOImpl = new WorkDAOImpl();
        return workDAOImpl;
    }

    @Override
    public boolean insert(Work work) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            return executeUpdatePrepareStatement(ps,
                    Dao.STRING + work.getTitle(),
                    Dao.STRING + work.getDescription(),
                    Dao.DOUBLE + work.getRequiredMoney(),
                    Dao.STRING + work.getStatus().name(),
                    Dao.DATE + work.getStartDate(),
                    Dao.DATE + work.getFinishDate(),
                    Dao.INTEGER + work.getTeamId(),
                    Dao.INTEGER + work.getBlockId()
            );
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        throw new MyException("Work can not be deleted");
    }

    @Override
    public Optional<Work> findById(int id) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            return getOptionalWork(executePrepareStatement(ps, Dao.INTEGER + id));
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public List<Work> findAll() {

        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {
            List<Work> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next())
                list.add(getWorkFromResultSet(resultSet));

            return list;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }

    }

    @Override
    public List<WorkViewDTO> findAllForView() {

        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            List<WorkViewDTO> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_FOR_VIEW);

            while (resultSet.next())
                list.add(getWorkViewFromResultSet(resultSet));

            return list;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }

    }

    @Override
    public boolean update(int id, Work work) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection()) {
            return checkAndUpdateWork(connection, id, work);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Work> findByTitle(String title) {
        List<Work> list = new ArrayList<>();
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE)) {

            ResultSet resultSet = executePrepareStatement(
                    ps, Dao.STRING + title);

            while (resultSet.next())
                list.add(getWorkFromResultSet(resultSet));

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Work> findAllReported() {
        List<Work> list = new ArrayList<>();
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_STATUS)) {

            ResultSet resultSet = executePrepareStatement(
                    ps, Dao.STRING + Status.REPORTED);

            while (resultSet.next())
                list.add(getWorkFromResultSet(resultSet));

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Work> findAllFinished() {
        List<Work> list = new ArrayList<>();
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_STATUS)) {

            ResultSet resultSet = executePrepareStatement(
                    ps, Dao.STRING + Status.FINISHED);

            while (resultSet.next())
                list.add(getWorkFromResultSet(resultSet));

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Work> findAllByBlockId(Integer blockId) {
        return getWorks(blockId, FIND_ALL_BY_BLOCK_ID);
    }

    @Override
    public List<Work> findAllByTeamId(Integer teamId) {
        return getWorks(teamId, FIND_ALL_BY_TEAM_ID);
    }

    @Override
    public boolean setStatusToReported(Integer id) {
        return setStatusTo(id, Status.REPORTED);
    }

    @Override
    public boolean setStatusToCancelled(Integer id) {
        return setStatusTo(id, Status.CANCELLED);
    }

    @Override
    public boolean setStatusToFinished(Integer id) {
        return setStatusTo(id, Status.FINISHED);
    }

    @Override
    public Optional<WorkDTO> getWorkDTOById(Integer id) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_FOR_DTO)) {
            var resultSet = executePrepareStatement(ps, Dao.INTEGER + id);
            if (resultSet.next())
                return Optional.of(getWorkFromResultSetForDTO(resultSet));
            return Optional.empty();
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }

    }

    private ResultSet executePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args)
                .executeQuery();
    }

    private boolean executeUpdatePrepareStatement(PreparedStatement ps, String... args) throws SQLException {
        return putArgs(ps, args)
                .executeUpdate() > 0;
    }

    private PreparedStatement putArgs(PreparedStatement ps, String... args) throws SQLException {
        return Dao.setPSArgs(ps, args);
    }

    private List<Work> getWorks(Integer blockId, String findAllByBlockId) {
        List<Work> list = new ArrayList<>();
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(findAllByBlockId)) {

            ResultSet resultSet = executePrepareStatement(
                    ps, Dao.INTEGER + blockId);

            while (resultSet.next())
                list.add(getWorkFromResultSet(resultSet));

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Work> getOptionalWork(ResultSet rs) {
        try {
            if (rs.next()) {
                Work work;
                work = getWorkFromResultSet(rs);
                if (work.getId() != null)
                    return Optional.of(work);
            }
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return Optional.empty();
    }

    private Work getWorkFromResultSet(ResultSet rs) throws SQLException {
        Work work = new Work();
        work.setId(rs.getInt("id"));
        work.setStatus(Status.valueOf(rs.getString("status")));
        work.setDescription(rs.getString("description"));
        work.setTitle(rs.getString("title"));
        work.setBlockId(rs.getInt("block_id"));
        work.setStar(rs.getInt("star"));
        work.setRequiredMoney(rs.getDouble("required_money"));
        work.setStartDate(rs.getDate("start_date").toLocalDate());
        work.setFinishDate(rs.getDate("finish_date").toLocalDate());
        work.setTeamId(rs.getInt("team_id"));
        return work;
    }

    private WorkDTO getWorkFromResultSetForDTO(ResultSet rs) throws SQLException {

        return WorkDTO.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .status(Status.valueOf(rs.getString("status")))
                .blockName(rs.getString("blockName"))
                .star(rs.getInt("star"))
                .requiredMoney(rs.getDouble("required_money"))
                .startDate(rs.getDate("start_date"))
                .finishDate(rs.getDate("finish_date"))
                .teamName(rs.getString("teamName"))
                .build();
    }

    private WorkViewDTO getWorkViewFromResultSet(ResultSet rs) throws SQLException {
        WorkViewDTO workViewDTO = new WorkViewDTO();
        workViewDTO.setId(rs.getInt("id"));
        workViewDTO.setTeamName(rs.getString("teamName"));
        workViewDTO.setTitle(rs.getString("title"));
        workViewDTO.setBlockName(rs.getString("blockName"));

        return workViewDTO;
    }

    private boolean setStatusTo(int workId, Status status) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SET_STATUS_TO)) {
            return executeUpdatePrepareStatement(ps, Dao.INTEGER + workId, Dao.STRING + status.name());
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    private boolean existById(Connection connection, int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet resultSet = executePrepareStatement(ps, Dao.INTEGER + id);

            if (resultSet.next())
                return resultSet.getInt("id") == 0;

            return false;
        }
    }

    private boolean checkAndUpdateWork(Connection connection, int id, Work work) throws SQLException {
        if (!existById(connection, id))
            return false;

        if (blockDao.findById(work.getBlockId()).isEmpty())
            throw new MyException("Block Not Found");

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_WORK)) {
            return executeUpdatePrepareStatement(ps,
                    Dao.STRING + work.getTitle(),
                    Dao.STRING + work.getDescription(),
                    Dao.INTEGER + work.getStar(),
                    Dao.DOUBLE + work.getRequiredMoney(),
                    Dao.DATE + work.getStartDate(),
                    Dao.DATE + work.getFinishDate(),
                    Dao.INTEGER + work.getBlockId(),
                    Dao.INTEGER + work.getTeamId());
        }
    }

}
