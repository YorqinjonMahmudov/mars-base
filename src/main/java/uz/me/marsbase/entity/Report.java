package uz.me.marsbase.entity;

import lombok.*;
import uz.me.marsbase.entity.abs.AbsEntity;

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
