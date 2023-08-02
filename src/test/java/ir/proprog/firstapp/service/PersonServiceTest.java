package ir.proprog.firstapp.service;

import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.sevice.PersonPredicate;
import ir.proprog.firstapp.sevice.PersonService;
import ir.proprog.firstapp.sevice.PersonServiceFactory;
import ir.proprog.firstapp.util.TestDataUtil;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class PersonServiceTest {
    private List<Person> personList;
    private PersonService personService = PersonServiceFactory.getPersonService();
    @Before
    public void init() {
        personList = TestDataUtil.getPersonList();
    }

    @Test
    public void save_person_successfully() {
        assertThat(new ArrayList<>(), hasSize(1));
    }
    @Test
    public void filter_legal_person_successfully() {
        assertThat(personService.filterPerson(personList, person -> Objects.equals("LEGAL", person.getType())), hasSize(2));
    }

    @Test
    public void filter_legal_person_successfully_by_function() {
        Function<Person, Boolean> p1 = person -> Objects.equals("LEGAL", person.getType());
        Function<Person, Boolean> p2 = person -> Objects.equals("1401/01/01", person.getBirthDate());
        Function<Person, Boolean> f = person -> p1.apply(person) && p2.apply(person);

        Predicate<Person> predicateByType = person -> Objects.equals("REAL", person.getType());
        Predicate<Person> predicateByName = person -> Objects.equals("Ali", person.getName());
        Predicate<Person> predicateByNameAndType = predicateByType.and(predicateByName);

        PersonPredicate f3 = person -> Objects.equals("LEGAL", person.getType());

        assertThat(personService.filterPersonFunctional(personList, predicateByNameAndType), hasSize(1));
        assertThat(personService.filterPerson(personList, f3), hasSize(2));

        assertThat(personService.filterPerson(personList, (person -> f.apply(person))), hasSize(2));
        // by method reference or double column
        assertThat(personService.filterPerson(personList, f::apply), hasSize(2));

    }
}
