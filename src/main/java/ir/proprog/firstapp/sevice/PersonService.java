package ir.proprog.firstapp.sevice;

import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;

public interface PersonService {
    List<PersonDTO> getAllPerson();
    
    PersonDTO getPersonData(String code) throws BusinessException;

    @Transactional
    PersonDTO savePerson(String name, String code, String type);

    List<Person> filterPerson(List<Person> personList, PersonPredicate predicate);

    List<Person> filterPersonFunctional(List<Person> personList, Predicate<Person> predicate);
}
