package uz.me.marsbase.entity;

import lombok.*;
import uz.me.marsbase.entity.abs.AbsEntity;
import uz.me.marsbase.entity.enums.Status;

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
