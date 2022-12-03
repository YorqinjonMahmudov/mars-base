package uz.me.marsbase.model.dto;

import lombok.*;
import uz.me.marsbase.model.entity.enums.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private Integer blockId;
}
