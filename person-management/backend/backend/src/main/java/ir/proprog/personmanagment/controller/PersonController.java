package ir.proprog.personmanagment.controller;

import ir.proprog.personmanagment.dto.PersonDTO;
import ir.proprog.personmanagment.exception.BusinessException;
import ir.proprog.personmanagment.sevice.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonServiceImpl personServiceImpl;

    @GetMapping("/getAllPersons")
    public ResponseEntity<List<PersonDTO>> all() {
        List<PersonDTO> allPerson = personServiceImpl.getAllPerson();
        ResponseEntity<List<PersonDTO>> personsResponseEntity = ResponseEntity.ok(allPerson);
        return personsResponseEntity;
    }

    @GetMapping("/getPerson")
    public ResponseEntity<PersonDTO> getPerson(@RequestParam String code) throws BusinessException {
        PersonDTO person = personServiceImpl.getPersonData(code);
        ResponseEntity<PersonDTO> personResponseEntity = ResponseEntity.ok(person);
        LOGGER.info(person.toString() + " CREATE SUCCESSFULLY");
        return personResponseEntity;
    }

    @PostMapping("/addPerson")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO person) {
        PersonDTO savedPerson = personServiceImpl.savePerson(person);
        ResponseEntity<PersonDTO> personResponseEntity = new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
        return personResponseEntity;
    }
}
