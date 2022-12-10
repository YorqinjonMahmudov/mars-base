package uz.me.marsbase.payload;

import lombok.*;
import uz.me.marsbase.model.entity.enums.Status;

import java.util.Date;

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
