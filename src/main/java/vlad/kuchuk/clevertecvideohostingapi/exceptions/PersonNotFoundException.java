package vlad.kuchuk.clevertecvideohostingapi.exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {
        super();
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
