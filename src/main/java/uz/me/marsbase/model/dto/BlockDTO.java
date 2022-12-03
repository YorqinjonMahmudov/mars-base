package uz.me.marsbase.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockDTO {

    private Integer id;

    private String name;

    private double area;

    private String location;
}
