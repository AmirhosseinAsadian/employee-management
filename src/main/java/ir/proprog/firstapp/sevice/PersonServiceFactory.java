package ir.proprog.firstapp.sevice;

public class PersonServiceFactory {

    public static PersonServiceImpl getPersonService() {
        return new PersonServiceImpl();
    }
}
