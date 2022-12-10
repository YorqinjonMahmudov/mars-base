package uz.me.marsbase.model.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.exception.MyException;
import uz.me.marsbase.model.dao.Dao;
import uz.me.marsbase.model.dao.ReportDao;
import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.payload.ReportDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportDAOImpl implements ReportDao {

    private static final String INSERT = "INSERT INTO report (work_id, date, team_id ) VALUES(?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, workId, date, team_id FROM report WHERE id = ?;";
    private static final String FIND_ALL = "SELECT id, workId, date, team_id FROM report;";
    private static final String FIND_BY_WORK_ID_FOR_DTO = "SELECT r.id id, r.work_id workId, r.date reportedDate, comments, t.name teamName FROM report r JOIN team t on t.id = r.team_id WHERE work_id = ?;";
    private static final String FIND_BY_WORK_ID = "SELECT id, workId, date, team_id FROM report WHERE work_id = ?;";
    private static final String UPDATE = "UPDATE report SET date = ?, comments = ? WHERE id = ?;";
    private static ReportDAOImpl reportDAOImpl;

    private ReportDAOImpl() {
    }

    public static ReportDAOImpl getInstance() {
        if (reportDAOImpl == null)
            reportDAOImpl = new ReportDAOImpl();
        return reportDAOImpl;
    }


    @Override
    public boolean insert(Report report) {

        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(INSERT)) {
            return putArgs(ps, Dao.STRING + report.getComments(),
                    Dao.INTEGER + report.getTeamId(),
                    Dao.INTEGER + report.getWorkId(),
                    Dao.DATE + report.getDate()).executeUpdate() > 0;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    /**
     * users can't delete report but can update date and comments
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Optional<Report> findById(int id) {
        return getReport(id, FIND_BY_ID);
    }

    @Override
    public List<Report> findAll() {
        return getReports(FIND_ALL);
    }

    private List<Report> getReports(String findAll) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(findAll)) {
            ResultSet resultSet = ps.executeQuery();
            List<Report> reports = new ArrayList<>();
            while (resultSet.next())
                reports.add(mapRsToReport(resultSet));
            return reports;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean update(int id, Report report) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(UPDATE)) {
            return putArgs(ps,
                    Dao.INTEGER + report.getTeamId(),
                    Dao.INTEGER + report.getWorkId(),
                    Dao.STRING + report.getComments(),
                    Dao.DATE + report.getDate()).executeUpdate() > 0;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    private PreparedStatement putArgs(PreparedStatement ps, String... args) throws SQLException {
        return Dao.setPSArgs(ps, args);
    }

    private Report mapRsToReport(ResultSet rs) throws SQLException {
        Report report = new Report();
        report.setId(rs.getInt("id"));
        report.setDate(rs.getDate("date"));
        report.setComments(rs.getString("comments"));
        report.setWorkId(rs.getInt("work_id"));
        report.setWorkId(rs.getInt("team_id"));
        return report;
    }

    private ReportDTO mapRsToReportDTO(ResultSet rs) throws SQLException {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(rs.getInt("id"));
        reportDTO.setReportedDate(rs.getDate("reportedDate"));
        reportDTO.setComments(rs.getString("comments"));
        reportDTO.setWorkId(rs.getInt("workId"));
        reportDTO.setTeamName(rs.getString("teamName"));
        return reportDTO;
    }

    @Override
    public Optional<ReportDTO> findByWorkId(Integer workId) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_WORK_ID_FOR_DTO)) {
            ResultSet resultSet = putArgs(ps, Dao.INTEGER + workId).executeQuery();
            if (resultSet.next())
                return Optional.of(mapRsToReportDTO(resultSet));
            return Optional.empty();
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    private Optional<Report> getReport(Integer teamId, String sql) {
        try (Connection connection = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = putArgs(ps, Dao.INTEGER + teamId).executeQuery();
            Report report;
            if (rs.next() && (report = mapRsToReport(rs)).getId() != null)
                return Optional.of(report);
            else return Optional.empty();
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }
}
