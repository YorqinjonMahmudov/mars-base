package uz.me.marsbase.service;

import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.model.dtos.WorkDTO;
import uz.me.marsbase.model.dtos.WorkViewDTO;

import java.util.List;

public interface WorkService {

    List<WorkViewDTO> getWorks();

    WorkDTO findById(Integer id);

    List<WorkViewDTO> getByTeamName(String teamName);
    List<WorkViewDTO> getByTeamLeadId(Integer teamLeadId);

    List<WorkViewDTO> getByBlockName(String teamName);

    boolean insert(Work work);

    boolean update(Integer editingWorkId, Work work);

    boolean delete(int deletingWorkId);
}
