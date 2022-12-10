package uz.me.marsbase.model.dao;


import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.payload.ReportDTO;

import java.util.List;
import java.util.Optional;

public interface ReportDao extends Dao<Report> {


    Optional<ReportDTO> findByWorkId(Integer workId);

}
