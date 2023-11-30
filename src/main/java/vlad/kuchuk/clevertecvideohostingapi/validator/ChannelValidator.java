package vlad.kuchuk.clevertecvideohostingapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vlad.kuchuk.clevertecvideohostingapi.dto.ChannelDto;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonDto;
import vlad.kuchuk.clevertecvideohostingapi.exceptions.AlreadyExistsException;
import vlad.kuchuk.clevertecvideohostingapi.exceptions.InvalidChannelCategoryException;
import vlad.kuchuk.clevertecvideohostingapi.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.service.ChannelService;
import vlad.kuchuk.clevertecvideohostingapi.service.PersonService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChannelValidator implements ConstraintValidator<ChannelValidation, ChannelDto> {

    private final PersonService personService;
    private final ChannelService channelService;
    private static final List<String> validCategories = List.of(
            "funny", "news", "programming", "it", "cartoons", "movies"
    );

    @Override
    public void initialize(ChannelValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ChannelDto value, ConstraintValidatorContext context) {
        if (Objects.nonNull(value.getAuthor())) {
            Optional<PersonDto> personByEmail = personService.getPersonByEmail(value.getAuthor().getEmail());
            personByEmail.orElseThrow(() -> new PersonNotFoundException("No user with such email"));

            personByEmail.flatMap(personDto -> channelService.getChannelByName(value.getName())).ifPresent(channel -> {
                throw new AlreadyExistsException("Channel name is taken");
            });
        }

        if (value.getCategory() != null && !validCategories.contains(value.getCategory().toLowerCase().trim())) {
            throw new InvalidChannelCategoryException("Invalid channel category");
        }

        return true;
    }
}