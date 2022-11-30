package uz.me.marsbase.entity;

import lombok.*;
import uz.me.marsbase.entity.abs.AbsEntity;

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
