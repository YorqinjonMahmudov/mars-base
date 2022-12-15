package uz.me.marsbase.service;

import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dao.ReportDao;
import uz.me.marsbase.model.dao.WorkDao;
import uz.me.marsbase.utils.mappers.ReportMapper;
import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.model.dtos.ReportDTO;

import java.util.List;
import java.util.Optional;

public class ReportServiceImpl implements ReportService {

    private static final ReportDao reportDao = InstanceHolder.getInstance(ReportDao.class);
    private static final WorkDao workDao = InstanceHolder.getInstance(WorkDao.class);
    private static final ReportMapper reportMapper = ReportMapper.getInstance();

    @Override
    public List<ReportDTO> getReports() {
        return null;
    }

    @Override
    public ReportDTO findById(Integer id) {
        Optional<Report> optionalReport = reportDao.findById(id);
        return reportMapper.toDto(optionalReport.orElse(null));
    }

    @Override
    public ReportDTO findByWorkId(Integer reportId) {
        return reportDao.findByWorkId(reportId).orElse(null);
    }

    @Override
    public ReportDTO insert(Report report) {
        var insert = reportDao.insert(report);
        if (insert) {
            Optional<ReportDTO> optionalReportDTO = reportDao.findByWorkId(report.getWorkId());
            if (optionalReportDTO.isEmpty())
                return null;
            var reportDTO = optionalReportDTO.get();
            workDao.setStatusToReported(reportDTO.getWorkId());
            return reportDTO;
        }
        return null;
    }

    @Override
    public boolean update(Integer editingReportId, Report report) {
        return reportDao.update(editingReportId, report);
    }

    @Override
    public boolean delete(int deletingReportId) {
        return reportDao.delete(deletingReportId);
    }
}
