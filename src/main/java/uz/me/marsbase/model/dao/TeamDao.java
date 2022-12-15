package uz.me.marsbase.model.dao;

import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.exception.MyException;

import java.util.List;
import java.util.Optional;

public interface TeamDao extends Dao<Team> {
    /**
     * Find Team by name.
     *
     * @param name Category name.
     * @return Category obj if exists with this id, otherwise Optional.empty().
     * @throws MyException if an error occurs.
     */
    Optional<Team> findByName(String name);

    List<Team> findByTeamLeadId(Integer teamLeadId);
}
