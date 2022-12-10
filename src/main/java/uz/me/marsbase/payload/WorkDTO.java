package uz.me.marsbase.payload;

import lombok.*;
import uz.me.marsbase.model.entity.enums.Status;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkDTO {

    private Integer id;

    private String title;

    private String description;

    private Double requiredMoney;

    private Status status;

    private Date startDate;

    private Date finishDate;

    private Integer star;

    private String teamName;

    private String blockName;
}
