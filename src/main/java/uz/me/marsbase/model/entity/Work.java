package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;
import uz.me.marsbase.model.entity.enums.Status;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Work extends AbsEntity {

    private String title;

    private String description;

    private Double requiredMoney;

    private Status status;

    private Date startDate;

    private Date finishDate;

    private Integer star;

    private Integer teamId;

    private Integer blockId;

}
