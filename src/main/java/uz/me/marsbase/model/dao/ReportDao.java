package uz.me.marsbase.model.dao;


import uz.me.marsbase.model.entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportDao extends Dao<Report> {

    List<Report> findByTeamId(Integer teamId) ;
    Optional<Report> findByWorkId(Integer teamId) ;

}
