package vlad.kuchuk.clevertecvideohostingapi.utils;

import lombok.experimental.UtilityClass;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PhotoOperationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@UtilityClass
public class ImageUtils {

    public static final int BYTE_SIZE = 4 * 1024;

    public static byte[] compressImage(byte[] data) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            Deflater deflater = new Deflater();
            deflater.setLevel(Deflater.BEST_COMPRESSION);
            deflater.setInput(data);
            deflater.finish();

            byte[] tmp = new byte[BYTE_SIZE];

            while (!deflater.finished()) {
                int size = deflater.deflate(tmp);
                outputStream.write(tmp, 0, size);
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new PhotoOperationException("Error while compressing image: " + e.getMessage());
        }
    }

    public static byte[] decompressImage(byte[] data) {
        if (Objects.isNull(data)) {
            return new byte[0];
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            Inflater inflater = new Inflater();
            inflater.setInput(data);

            byte[] tmp = new byte[BYTE_SIZE];

            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }

            return outputStream.toByteArray();
        } catch (IOException | DataFormatException e) {
            throw new PhotoOperationException("Error while decompressing image: " + e.getMessage());
        }
    }
}
