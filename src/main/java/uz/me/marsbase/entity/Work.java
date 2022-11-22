package uz.me.marsbase.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.me.marsbase.entity.enums.Status;

import java.util.Date;

@Getter
@Setter
@Builder
public class Work {

    private Integer id;

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
