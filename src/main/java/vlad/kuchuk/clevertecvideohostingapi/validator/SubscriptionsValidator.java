package vlad.kuchuk.clevertecvideohostingapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonOperationException;
import vlad.kuchuk.clevertecvideohostingapi.dto.SubscriptionDto;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;
import vlad.kuchuk.clevertecvideohostingapi.repository.ChannelRepository;
import vlad.kuchuk.clevertecvideohostingapi.repository.PersonRepository;

@Component
@RequiredArgsConstructor
public class SubscriptionsValidator  implements ConstraintValidator<SubscriptionsValidation, SubscriptionDto> {

    private final ChannelRepository channelRepository;
    private final PersonRepository personRepository;

    @Override
    public void initialize(SubscriptionsValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SubscriptionDto subscriptionDto, ConstraintValidatorContext context) {

        Person user = personRepository.findById(subscriptionDto.getPersonId())
                .orElseThrow(() -> new PersonNotFoundException("User not found with id=" + subscriptionDto.getPersonId()));

        Channel channel = channelRepository.findById(subscriptionDto.getChannelId())
                .orElseThrow(() -> new ChannelNotFoundException("Channel not found with id=" + subscriptionDto.getChannelId()));

        if (channel.getAuthor().equals(user))
            throw new PersonOperationException("Channel author can't subscribe his channel");

        if (user.getSubscriptions().contains(channel))
            throw new PersonOperationException("You have already subscribed this channel");

        return true;
    }
}
