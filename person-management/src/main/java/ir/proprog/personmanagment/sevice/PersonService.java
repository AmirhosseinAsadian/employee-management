package ir.proprog.personmanagment.sevice;

import ir.proprog.personmanagment.domain.Person;
import ir.proprog.personmanagment.dto.PersonDTO;
import ir.proprog.personmanagment.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;

public interface PersonService {
    List<PersonDTO> getAllPerson();
    
    PersonDTO getPersonData(String code) throws BusinessException;

    @Transactional
    PersonDTO savePerson(PersonDTO newPerson);

    List<Person> filterPerson(List<Person> personList, PersonPredicate predicate);

    List<Person> filterPersonFunctional(List<Person> personList, Predicate<Person> predicate);
}
