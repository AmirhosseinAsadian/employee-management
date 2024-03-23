package ir.proprog.personmanagment.sevice;

import ir.proprog.personmanagment.config.ConfigUtil;
import ir.proprog.personmanagment.domain.Person;
import ir.proprog.personmanagment.dto.PersonDTO;
import ir.proprog.personmanagment.exception.BusinessException;
import ir.proprog.personmanagment.exception.PersonErrorCode;
import ir.proprog.personmanagment.repository.PersonRedisRepository;
import ir.proprog.personmanagment.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<PersonDTO> personDTOList = personList.stream().map(person -> preparePersonDTO(person)).collect(Collectors.toList());
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
                Person person = personRepository.findByCode(code).orElseThrow(() -> new BusinessException("person with code: " + code + " not found.", PersonErrorCode.INVALID_PERSON_CODE, code));
                return preparePersonDTO(person);
            } else {
                return personDTO.get();
            }
        } else {
            Person person = personRepository.findByCode(code).orElseThrow(() -> new BusinessException("person with code: " + code + " not found.", PersonErrorCode.INVALID_PERSON_CODE, code));
            return preparePersonDTO(person);
        }
    }

    @Transactional
    @Override
    public PersonDTO savePerson(PersonDTO newPerson) {
        Person person = preparePerson(newPerson);
        Person savedPerson = personRepository.save(person);
        PersonDTO personDTO = preparePersonDTO(savedPerson);
        Boolean redisStatus = configUtil.getRedisStatus();
        if (Boolean.TRUE.equals(redisStatus)) {
            personRedisRepository.savePerson(personDTO);
        }
        return personDTO;
    }

    private Person preparePerson(PersonDTO newPerson) {
        Person person = new Person();
        BeanUtils.copyProperties(newPerson, person);
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
