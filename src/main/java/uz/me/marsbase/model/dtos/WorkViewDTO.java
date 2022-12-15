package uz.me.marsbase.model.dtos;

import lombok.*;
import uz.me.marsbase.model.entity.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkViewDTO {

    private Integer id;

    private String title;

    private Status status;

    private String teamName;

    private String blockName;
}
