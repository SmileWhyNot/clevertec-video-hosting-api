package vlad.kuchuk.clevertecvideohostingapi.exceptions;

public class ChannelNotFoundException extends RuntimeException {

    public ChannelNotFoundException() {
    }

    public ChannelNotFoundException(String message) {
        super(message);
    }
}
