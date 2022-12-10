package uz.me.marsbase.service;

import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.payload.ReportDTO;

import java.util.List;

public interface ReportService {

    List<ReportDTO> getReports();

    ReportDTO findById(Integer id);

    ReportDTO findByWorkId(Integer workId);

    boolean insert(Work work);

    boolean update(Integer editingWorkId, Work work);

    boolean delete(int deletingWorkId);
}
