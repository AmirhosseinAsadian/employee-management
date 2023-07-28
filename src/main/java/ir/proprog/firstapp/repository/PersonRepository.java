package ir.proprog.firstapp.repository;

import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.domain.dto.PersonDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCode(String code);
}
