package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block extends AbsEntity {

    private Integer id;

    private String name;

    private double area;

    private String location;

}
