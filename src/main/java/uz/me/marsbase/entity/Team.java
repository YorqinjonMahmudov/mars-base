package uz.me.marsbase.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Team {

    private Integer id;

    private String name;

    private Integer teamLeaderId;

}
