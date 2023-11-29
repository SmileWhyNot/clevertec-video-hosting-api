package vlad.kuchuk.clevertecvideohostingapi.exceptions;

public class PersonOperationException extends RuntimeException {
    public PersonOperationException() {
    }

    public PersonOperationException(String message) {
        super(message);
    }
}
