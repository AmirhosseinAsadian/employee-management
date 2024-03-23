package ir.proprog.personmanagment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PersonDTO {

    private String name;
    private String code;
    private String type;
    private String birthDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return name.equals(personDTO.name) && code.equals(personDTO.code) && type.equals(personDTO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, type);
    }

    @Override
    public String toString() {
        return "PERSON NAME='" + name + "', CODE='" + code + " ";
    }
}
