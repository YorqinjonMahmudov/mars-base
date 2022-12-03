package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report extends AbsEntity {

    private Integer workId;

    private Date date;

    private String comments;

    private Integer teamId;


}
