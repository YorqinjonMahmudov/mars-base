package uz.me.marsbase.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TeamDTO {
    private Integer id;

    private String name;

    private String  teamLeadEmail;

    private boolean active;
}
