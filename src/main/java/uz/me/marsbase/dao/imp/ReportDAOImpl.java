package uz.me.marsbase.dao.imp;

import uz.me.marsbase.connection.MyConnectionPool;
import uz.me.marsbase.dao.Dao;
import uz.me.marsbase.dao.ReportDao;
import uz.me.marsbase.entity.Report;
import uz.me.marsbase.exception.MyException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportDAOImpl implements ReportDao {

    private static final String INSERT = "INSERT INTO report (workId, date, team_id ) VALUES(?,?,?);";
    private static final String FIND_BY_ID = "SELECT id, workId, date, team_id FROM report WHERE id = ?;";
    private static final String FIND_ALL = "SELECT id, workId, date, team_id FROM report;";
    private static final String FIND_BY_TEAM_ID = "SELECT id, workId, date, team_id FROM report WHERE team_id = ?;";
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

    @Override
    public List<Report> findByTeamId(Integer teamId) {
        return getReports(FIND_BY_TEAM_ID);
    }

    @Override
    public Optional<Report> findByWorkId(Integer teamId) {
        return getReport(teamId, FIND_BY_WORK_ID);
    }

    private Optional<Report> getReport(Integer teamId, String sql) {
        try (PreparedStatement ps = MyConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
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
