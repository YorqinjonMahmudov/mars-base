package uz.me.marsbase.model.entity;

import lombok.*;
import uz.me.marsbase.model.entity.abs.AbsEntity;
import uz.me.marsbase.model.entity.enums.Role;

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

    public User(Integer id, String firstName, String lastName, String email, String password, Role role, Integer blockId) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.blockId = blockId;
    }
}
