package uz.me.marsbase.service;

import uz.me.marsbase.mappers.UserMapper;
import uz.me.marsbase.model.dao.WorkDao;
import uz.me.marsbase.model.dao.imp.WorkDAOImpl;
import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.payload.WorkDTO;
import uz.me.marsbase.payload.WorkViewDTO;

import java.util.List;

public class WorkServiceImpl implements WorkService {
    private static final WorkDao workDao = WorkDAOImpl.getInstance();
    private static final UserMapper userMapper = new UserMapper();

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
    public List<WorkViewDTO> getByBlockName(String teamName) {
        return null;
    }

    @Override
    public boolean insert(Work work) {
        return workDao.insert(work);
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
