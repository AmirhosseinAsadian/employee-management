package ir.proprog.firstapp.sevice;

import ir.proprog.firstapp.config.ConfigUtil;
import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.exception.BusinessException;
import ir.proprog.firstapp.repository.PersonRedisRepository;
import ir.proprog.firstapp.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;

@Service
@Qualifier
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRedisRepository personRedisRepository;

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
    public PersonDTO getPersonData(String code) throws BusinessException {
        PersonDTO person = findByCode(code);
        return person;
    }

    private PersonDTO findByCode(String code) throws BusinessException {
        Boolean redisStatus = configUtil.getRedisStatus();
        if (Boolean.TRUE.equals(redisStatus)) {
            Optional<PersonDTO> personDTO = personRedisRepository.findByCode(code);
            if (personDTO.isEmpty()) {
                Person person = personRepository.findByCode(code).orElseThrow(() -> new BusinessException("person with code: " + code + " not found.", "01", code));
                return preparePersonDTO(person);
            } else {
                return personDTO.get();
            }
        } else {
            Person person = personRepository.findByCode(code).orElseThrow(() -> new BusinessException("person with code: " + code + " not found.", "01", code));
            return preparePersonDTO(person);
        }
    }

    @Transactional
    @Override
    public PersonDTO savePerson(String name, String code, String type) {
        Person person = preparePerson(name, code, type);
        Person savedPerson = personRepository.save(person);
        PersonDTO personDTO = preparePersonDTO(savedPerson);
        Boolean redisStatus = configUtil.getRedisStatus();
        if (Boolean.TRUE.equals(redisStatus)) {
            personRedisRepository.savePerson(personDTO);
        }
        return personDTO;
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
