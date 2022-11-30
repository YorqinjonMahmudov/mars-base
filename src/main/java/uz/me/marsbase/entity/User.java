package uz.me.marsbase.entity;

import lombok.*;
import uz.me.marsbase.entity.abs.AbsEntity;
import uz.me.marsbase.entity.enums.Role;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbsEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private Integer blockId;

}
