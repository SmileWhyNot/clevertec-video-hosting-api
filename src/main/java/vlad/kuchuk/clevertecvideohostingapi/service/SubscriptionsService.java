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

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionsService {

    private final PersonRepository personRepository;
    private final ChannelRepository channelRepository;

    @Transactional
    public SubscriptionDto subscribe(SubscriptionDto subscriptionDto) {
        return Stream.of(subscriptionDto)
                .map(dto -> {
                            Person person = getPersonById(dto.getPersonId());
                            Channel channel = getChannelById(dto.getChannelId());
                            validateSubscription(person, channel);
                            person.getSubscriptions().add(channel);
                            return dto;
                        }
                )
                .findFirst()
                .orElseThrow(() -> new PersonOperationException("Failed to subscribe"));
    }

    @Transactional
    public void unsubscribe(SubscriptionDto subscriptionDto) {
        Person person = getPersonById(subscriptionDto.getPersonId());
        Channel channel = getChannelById(subscriptionDto.getChannelId());

        if (!person.getSubscriptions().contains(channel))
            throw new PersonOperationException("You haven't subscribed to this channel");

        person.getSubscriptions().remove(channel);
    }

    private Person getPersonById(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id=" + personId));
    }

    private Channel getChannelById(Long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new ChannelNotFoundException("Channel not found with id=" + channelId));
    }

    private void validateSubscription(Person person, Channel channel) {
        if (channel.getAuthor().equals(person))
            throw new PersonOperationException("Channel author can't subscribe to his channel");

        if (person.getSubscriptions().contains(channel))
            throw new PersonOperationException("You have already subscribed to this channel");
    }
}