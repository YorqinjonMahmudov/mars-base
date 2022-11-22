package uz.me.marsbase.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamMember {

    private Integer id;

    private Integer userId;

    private Integer teamId;

}
