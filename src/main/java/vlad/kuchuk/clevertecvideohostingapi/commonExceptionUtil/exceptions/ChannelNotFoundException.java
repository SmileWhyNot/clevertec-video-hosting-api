package vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions;

public class ChannelNotFoundException extends RuntimeException {

    public ChannelNotFoundException() {
    }

    public ChannelNotFoundException(String message) {
        super(message);
    }
}
