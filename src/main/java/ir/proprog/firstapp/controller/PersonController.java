package ir.proprog.firstapp.controller;

import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.sevice.PersonService;
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

    @Autowired
    private PersonService personService;

    @GetMapping("/getAllPerson")
    public List<PersonDTO> all() {
        return personService.getAllPerson();
    }

    @GetMapping("/getPerson")
    public PersonDTO getPerson(@RequestParam String code) {
        return personService.getPersonData(code);
    }

    @PostMapping("/addPerson")
    public PersonDTO addPerson(@RequestParam String name, @RequestParam String code, @RequestParam String type) {
        if (true) {
            throw new RuntimeException("hi");
        }
        return personService.savePerson(name, code, type);
    }
}
