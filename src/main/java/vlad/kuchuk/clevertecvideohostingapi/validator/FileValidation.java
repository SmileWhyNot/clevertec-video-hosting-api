package vlad.kuchuk.clevertecvideohostingapi.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileValidator.class)
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileValidation {
    String[] fileExtensions() default {};

    String message() default "Invalid file extension";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
