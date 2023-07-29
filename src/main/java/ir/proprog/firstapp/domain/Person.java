package ir.proprog.firstapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String type;

    public Person(String name, String code, String type) {
        this.name = name;
        this.code = code;
        this.type = type;
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
}
