package uz.me.marsbase.utils.mappers;


import uz.me.marsbase.model.entity.Report;
import uz.me.marsbase.model.dtos.ReportDTO;

import java.util.List;

public class ReportMapper implements BaseMapper<Report, ReportDTO> {

    private static ReportMapper userMapper;

    public static ReportMapper getInstance() {
        if (userMapper == null)
            userMapper = new ReportMapper();
        return userMapper;
    }

    @Override
    public ReportDTO toDto(Report report) {
        if (report == null) return null;
        return new ReportDTO(report.getId(), report.getWorkId(),
                report.getDate(), report.getComments()
        );
    }

    @Override
    public Report fromDto(ReportDTO reportDTO) {
        return new Report(
                reportDTO.getId(),
                reportDTO.getWorkId(),
                reportDTO.getReportedDate(),
                reportDTO.getComments());
    }

    @Override
    public List<Report> fromDto(List<ReportDTO> dto) {
        return dto
                .stream()
                .map(this::fromDto)
                .toList();
    }

    @Override
    public List<ReportDTO> toDto(List<Report> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .toList();
    }

}
