package uz.me.marsbase.service;

import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.model.dtos.ReportDTO;

import java.util.List;

public interface ReportService {

    List<ReportDTO> getReports();

    ReportDTO findById(Integer id);

    ReportDTO findByWorkId(Integer workId);

    ReportDTO insert(Report report);

    boolean update(Integer editingReportId,  Report report);

    boolean delete(int deletingReportId);
}
