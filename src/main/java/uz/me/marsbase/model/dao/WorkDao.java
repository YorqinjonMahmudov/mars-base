package uz.me.marsbase.model.dao;

import uz.me.marsbase.model.entity.Work;
import uz.me.marsbase.model.dtos.WorkDTO;
import uz.me.marsbase.model.dtos.WorkViewDTO;

import java.util.List;
import java.util.Optional;

public interface WorkDao extends Dao<Work> {

    List<WorkViewDTO> findAllForView();


    List<Work> findByTitle(String title);


    List<Work> findAllReported();

    List<Work> findAllFinished();

    List<Work> findAllByBlockId(Integer blockId);

    List<Work> findAllByTeamId(Integer blockId);

    boolean setStatusToReported(Integer id);
    boolean setStatusToCancelled(Integer id);

    boolean setStatusToFinished(Integer id);


    Optional<WorkDTO> getWorkDTOById(Integer id);

    List<WorkViewDTO> findAllForViewByTeamLeadId(Integer teamLeadId);
}
