package uz.me.marsbase.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.me.marsbase.entity.enums.Role;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private Role role;

    private Integer blockId;

}
