package uz.me.marsbase.service;

import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dao.ReportDao;
import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.payload.ReportDTO;

import java.util.List;

public class ReportServiceImpl  implements ReportService{

    private static final ReportDao reportDao= InstanceHolder.getInstance(ReportDao.class);

    @Override
    public List<ReportDTO> getReports() {
        return null;
    }

    @Override
    public ReportDTO findById(Integer id) {
        return null;
    }

    @Override
    public ReportDTO findByWorkId(Integer workId) {
        return reportDao.findByWorkId(workId).orElse(null);
    }

    @Override
    public boolean insert(Work work) {
        return false;
    }

    @Override
    public boolean update(Integer editingWorkId, Work work) {
        return false;
    }

    @Override
    public boolean delete(int deletingWorkId) {
        return false;
    }
}
