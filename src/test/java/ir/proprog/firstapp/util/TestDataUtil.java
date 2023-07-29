package ir.proprog.firstapp.util;

import ir.proprog.firstapp.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataUtil {
    public static List<Person> getPersonList() {
        return new ArrayList<>(Arrays.asList(
                new Person("Ali", "11", "REAL"),
                new Person("Amir", "12", "REAL"),
                new Person("Reza", "13", "REAL"),
                new Person("BenzCo", "21", "LEGAL"),
                new Person("BMWCo", "22", "LEGAL")
        ));
    }
}
