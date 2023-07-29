package ir.proprog.firstapp.sevice;

public class PersonServiceFactory {

    public static PersonService getPersonService() {
        return new PersonService();
    }
}
