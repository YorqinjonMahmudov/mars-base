package uz.me.marsbase.model.dao;

import uz.me.marsbase.model.entity.Work;

import java.util.List;

public interface WorkDao extends Dao<Work> {

    /**
     * Find an Work by name.
     *
     * @param title Item name.
     * @return Item obj if exists, otherwise Optional.empty().
     */
    List<Work> findByTitle(String title);


    List<Work> findAllReported();

    List<Work> findAllFinished();

    List<Work> findAllByBlockId(Integer blockId);

    List<Work> findAllByTeamId(Integer blockId);

    boolean setStatusToReported(Integer id);
    boolean setStatusToCancelled(Integer id);

    boolean setStatusToFinished(Integer id);


}
