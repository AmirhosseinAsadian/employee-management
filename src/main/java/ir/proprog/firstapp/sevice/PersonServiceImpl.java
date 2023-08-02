package ir.proprog.firstapp.sevice;

import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDTO> getAllPerson() {
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (Person person : personList) {
            personDTOList.add(preparePersonDTO(person));
        }
        return personDTOList;
    }

    @Override
    public PersonDTO getPersonData(String code) {
        PersonDTO person = findByCode(code);
        return person;
    }

    private PersonDTO findByCode(String code) {
        Person person = personRepository.findByCode(code).orElse(null);
        return preparePersonDTO(person);
    }

    @Transactional
    @Override
    public PersonDTO savePerson(String name, String code, String type) {
        Person person = preparePerson(name, code, type);
        personRepository.save(person);
        return preparePersonDTO(person);
    }

    private Person preparePerson(String name, String code, String type) {
        Person person = new Person();
        person.setCode(code);
        person.setName(name);
        person.setType(type);
        return person;
    }

    private PersonDTO preparePersonDTO(Person person) {
        PersonDTO personView = new PersonDTO();
        BeanUtils.copyProperties(person, personView);
        return personView;
    }

    @Override
    public List<Person> filterPerson(List<Person> personList, PersonPredicate predicate) {
        List<Person> filteredPersonList = new ArrayList<>();
        for (Person person : personList) {
            if (predicate.filterPerson(person)) {
                filteredPersonList.add(person);
            }
        }
        return filteredPersonList;
    }

    @Override
    public List<Person> filterPersonFunctional(List<Person> personList, Predicate<Person> predicate) {
        return filterPerson(personList, predicate::test);
    }

}
