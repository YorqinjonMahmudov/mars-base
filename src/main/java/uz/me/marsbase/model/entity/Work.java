package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;
import uz.me.marsbase.model.entity.enums.Status;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Work extends AbsEntity {

    private String title;

    private String description;

    private Status status;

    private LocalDate startDate;

    private LocalDate finishDate;

    private Integer star;

    private Integer teamId;

    private Integer blockId;

}
