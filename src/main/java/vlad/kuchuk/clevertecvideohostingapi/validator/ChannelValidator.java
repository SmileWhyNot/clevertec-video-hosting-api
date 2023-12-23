package vlad.kuchuk.clevertecvideohostingapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.AlreadyExistsException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.InvalidChannelCategoryException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.InvalidLangException;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.ChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.service.ChannelService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChannelValidator implements ConstraintValidator<ChannelValidation, ChannelRequest> {

    private final ChannelService channelService;
    private static final List<String> validCategories = List.of(
            "funny", "news", "programming", "it", "cartoons", "movies"
    );

    private static final List<String> validLanguages = List.of(
            "en", "ru", "es", "fr", "de", "ja", "ko", "zh", "ar", "pt", "hi", "it"
    );

    @Override
    public void initialize(ChannelValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ChannelRequest value, ConstraintValidatorContext context) {
        channelService.getChannelByName(value.getName()).ifPresent(channel -> {
            throw new AlreadyExistsException("Channel name is taken");
        });

        if (value.getCategory() != null && !validCategories.contains(value.getCategory().toLowerCase().trim())) {
            throw new InvalidChannelCategoryException("Invalid channel category");
        }

        if (value.getLang() != null && !validLanguages.contains(value.getLang().toLowerCase().trim())) {
            throw new InvalidLangException("Invalid lang provided");
        }

        return true;
    }
}
