package vlad.kuchuk.clevertecvideohostingapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PhotoOperationException;

import java.util.Arrays;

public class FileValidator implements ConstraintValidator<FileValidation, MultipartFile> {
    private String[] allowedExtensions;

    @Override
    public void initialize(FileValidation constraintAnnotation) {
        this.allowedExtensions = constraintAnnotation.fileExtensions();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null)
            return true;

        if (file.isEmpty()) {
            throw new PhotoOperationException("Photo file is empty");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new PhotoOperationException("Filename exception");
        }
        String fileExtension = FilenameUtils.getExtension(originalFilename);
        if (!Arrays.asList(allowedExtensions).contains(fileExtension.toLowerCase()))
            throw new PhotoOperationException("Not correct file extension");

        return true;
    }
}
