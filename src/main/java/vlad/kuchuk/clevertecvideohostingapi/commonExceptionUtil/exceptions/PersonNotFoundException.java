package vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {
        super();
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
