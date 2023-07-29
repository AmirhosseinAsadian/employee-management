package ir.proprog.firstapp.sevice;

import ir.proprog.firstapp.domain.Person;

public interface PersonPredicate {
    boolean filterPerson(Person person);
}
