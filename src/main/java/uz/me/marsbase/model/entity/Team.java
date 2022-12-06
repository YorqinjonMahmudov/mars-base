package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team extends AbsEntity {

    private String name;

    private Integer teamLeadId;

    private boolean isActive;

    public Team(Integer id, String name, Integer teamLeadId, boolean isActive) {
        super(id);
        this.name = name;
        this.teamLeadId = teamLeadId;
        this.isActive = isActive;
    }
}
