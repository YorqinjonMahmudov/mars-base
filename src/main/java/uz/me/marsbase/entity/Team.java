package uz.me.marsbase.entity;

import lombok.*;
import uz.me.marsbase.entity.abs.AbsEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team extends AbsEntity {

    private String name;

    private Integer teamLeadId;

    private boolean isActive;

}
