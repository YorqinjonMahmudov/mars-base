package uz.me.marsbase.payload;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
