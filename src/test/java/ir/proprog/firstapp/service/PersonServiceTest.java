package ir.proprog.firstapp.service;

import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.sevice.PersonService;
import ir.proprog.firstapp.sevice.PersonServiceFactory;
import ir.proprog.firstapp.util.TestDataUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonServiceTest {
    private List<Person> personList;
    private PersonService personService = PersonServiceFactory.getPersonService();
    @Before
    public void init() {
        personList = TestDataUtil.getPersonList();
    }

    @Test
    public void save_person_successfully() {
        Assert.assertThat(new ArrayList<>(), hasSize(1));
    }
    @Test
    public void filter_legal_person_successfully() {
        Assert.assertThat(personService.filterPerson(personList, person -> Objects.equals("LEGAL", person.getType())), hasSize(2));
    }
}
