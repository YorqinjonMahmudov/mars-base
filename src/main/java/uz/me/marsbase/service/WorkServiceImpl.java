package uz.me.marsbase.service;

import uz.me.marsbase.model.dao.WorkDao;
import uz.me.marsbase.model.dao.imp.WorkDAOImpl;
import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.model.dtos.WorkDTO;
import uz.me.marsbase.model.dtos.WorkViewDTO;

import java.util.List;

public class WorkServiceImpl implements WorkService {
    private static final WorkDao workDao = WorkDAOImpl.getInstance();

    @Override
    public List<WorkViewDTO> getWorks() {
        return workDao.findAllForView();
    }

    @Override
    public WorkDTO findById(Integer id) {
        return workDao.getWorkDTOById(id).orElse(null);
    }

    @Override
    public List<WorkViewDTO> getByTeamName(String teamName) {
        return null;
    }

    @Override
    public List<WorkViewDTO> getByTeamLeadId(Integer teamLeadId) {
        return workDao.findAllForViewByTeamLeadId(teamLeadId);
    }

    @Override
    public List<WorkViewDTO> getByBlockName(String teamName) {
        return null;
    }

    @Override
    public boolean insert(Work work) {
        return workDao.insert(work);
    }

    @Override
    public boolean update(Integer editingWorkId, Work work) {
        return workDao.update(editingWorkId, work);
    }

    @Override
    public boolean delete(int deletingWorkId) {
        return workDao.delete(deletingWorkId);
    }
}
