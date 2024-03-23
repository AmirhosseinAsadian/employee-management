package ir.proprog.personmanagment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "t_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_person_seq")
    @SequenceGenerator(name = "t_person_seq", sequenceName = "t_person_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String code;
    private String type;
    private String birthDate;

    public Person(String name, String code, String type, String birthDate) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
