package ir.proprog.personmanagment.sevice;

import ir.proprog.personmanagment.domain.Person;

public interface PersonPredicate {
    boolean filterPerson(Person person);
}
