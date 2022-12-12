package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report extends AbsEntity {

    private Integer workId;

    private LocalDate date;

    private String comments;

    public Report(Integer id, Integer workId, LocalDate date, String comments) {
        super(id);
        this.workId = workId;
        this.date = date;
        this.comments = comments;
    }
}
