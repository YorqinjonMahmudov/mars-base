package uz.me.marsbase.payload;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDTO {

    private Integer id;

    private Integer workId;

    private Date reportedDate;

    private String comments;

    private String teamName;

}
