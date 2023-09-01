package ir.proprog.firstapp.controller;

import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.exception.BusinessException;
import ir.proprog.firstapp.sevice.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonServiceImpl personServiceImpl;

    @GetMapping("/getAllPerson")
    public List<PersonDTO> all() {
        return personServiceImpl.getAllPerson();
    }

    @GetMapping("/getPerson")
    public PersonDTO getPerson(@RequestParam String code) throws BusinessException {
        return personServiceImpl.getPersonData(code);
    }

    @PostMapping("/addPerson")
    public PersonDTO addPerson(@RequestParam String name, @RequestParam String code, @RequestParam String type) {
        return personServiceImpl.savePerson(name, code, type);
    }
}
