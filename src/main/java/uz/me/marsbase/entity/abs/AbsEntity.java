package uz.me.marsbase.entity.abs;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AbsEntity {
    private Integer id;

    public AbsEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
