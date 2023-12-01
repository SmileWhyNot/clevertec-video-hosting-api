package vlad.kuchuk.clevertecvideohostingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonOperationException;
import vlad.kuchuk.clevertecvideohostingapi.dto.SubscriptionDto;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;
import vlad.kuchuk.clevertecvideohostingapi.repository.ChannelRepository;
import vlad.kuchuk.clevertecvideohostingapi.repository.PersonRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionsService {

    private final PersonRepository personRepository;
    private final ChannelRepository channelRepository;

    @Transactional
    public SubscriptionDto subscribe(SubscriptionDto subscriptionDto) {
        personRepository.findById(subscriptionDto.getPersonId())
                .ifPresent(person ->
                        channelRepository.findById(subscriptionDto.getChannelId())
                                .ifPresent(channel -> person.getSubscriptions().add(channel)));

        return subscriptionDto;
    }
}
