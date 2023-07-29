package ir.proprog.firstapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private Long id;
    private String name;
    private String code;

    private String type;

    @Override
    public String toString() {
        return name + " " + code;
    }
}
