package uz.me.marsbase.model.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDTO {

    private Integer id;

    private Integer workId;

    private LocalDate reportedDate;

    private String comments;

}
