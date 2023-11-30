package vlad.kuchuk.clevertecvideohostingapi.exceptions;

public class InvalidChannelCategoryException extends RuntimeException {

    public InvalidChannelCategoryException() {
    }

    public InvalidChannelCategoryException(String message) {
        super(message);
    }
}
