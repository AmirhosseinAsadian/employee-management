package ir.proprog.personmanagment.util;

import ir.proprog.personmanagment.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataUtil {
    public static List<Person> getPersonList() {
        return new ArrayList<>(Arrays.asList(
                new Person("Ali", "11", "REAL", "1401/01/01"),
                new Person("Amir", "12", "REAL", "1401/01/01"),
                new Person("Reza", "13", "REAL", "1401/01/01"),
                new Person("BenzCo", "21", "LEGAL", "1401/01/01"),
                new Person("BMWCo", "22", "LEGAL", "1401/01/01")
        ));
    }
}
