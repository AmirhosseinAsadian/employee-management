package ir.proprog.personmanagment.sevice;

public class PersonServiceFactory {
    public static PersonServiceImpl getPersonService() {
        return new PersonServiceImpl();
    }
}
