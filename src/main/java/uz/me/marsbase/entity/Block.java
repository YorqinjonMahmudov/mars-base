package uz.me.marsbase.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Block {

    private Integer id;

    private String name;

    private double area;

    private String location;

}
