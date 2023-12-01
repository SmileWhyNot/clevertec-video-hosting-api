package vlad.kuchuk.clevertecvideohostingapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.AlreadyExistsException;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonDto;
import vlad.kuchuk.clevertecvideohostingapi.service.PersonService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonValidator implements ConstraintValidator<PersonValidation, PersonDto> {

    private final PersonService personService;

    @Override
    public void initialize(PersonValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonDto value, ConstraintValidatorContext context) {
        Optional<PersonDto> existingPersonByEmail = personService.getPersonByEmail(value.getEmail());
        Optional<PersonDto> existingPersonByNickname = personService.getPersonByNickname(value.getNickname());

        existingPersonByEmail.ifPresent(personDto -> {
            throw new AlreadyExistsException("Email is already taken");
        });

        existingPersonByNickname.ifPresent(personDto -> {
            throw new AlreadyExistsException("Nickname is already taken");
        });

        return true;
    }

}
