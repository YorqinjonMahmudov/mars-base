package uz.me.marsbase.entity;

import lombok.*;
import uz.me.marsbase.entity.abs.AbsEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember  extends AbsEntity {

    private Integer userId;

    private Integer teamId;

}
