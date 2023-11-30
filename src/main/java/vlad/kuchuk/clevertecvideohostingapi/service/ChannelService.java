package vlad.kuchuk.clevertecvideohostingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vlad.kuchuk.clevertecvideohostingapi.dto.ChannelDto;
import vlad.kuchuk.clevertecvideohostingapi.dto.ChannelMapper;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonDto;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonMapper;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelOperationException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.repository.ChannelRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final PersonService personService;
    private final ChannelMapper channelMapper;
    private final PersonMapper personMapper;

    public ChannelDto saveChannel(ChannelDto channelDto) {
        if (Objects.isNull(channelDto.getAuthor()))
            throw new PersonNotFoundException("No email provided to link with channel");

        return Stream.of(channelMapper.toEntity(channelDto))
                .map(channel -> {
                    PersonDto author = personService.getPersonByEmail(channel.getAuthor().getEmail())
                            .orElseThrow(() -> new PersonNotFoundException("No person with email"));
                    channel.setAuthor(personMapper.toEntity(author));
                    return channelRepository.save(channel);
                })
                .map(channelMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new ChannelOperationException("Failed to create new person account"));
    }

    public ChannelDto updateChannel(ChannelDto updatedChannel, Long id) {
        return channelRepository.findById(id)
                .map(p -> channelMapper.updateFromDto(updatedChannel, p))
                .map(channelRepository::save)
                .map(channelMapper::toDto)
                .orElseThrow(() -> new ChannelNotFoundException("No channel with id=" + id));
    }


    public void deleteChannel(Long id) {
        Optional.of(getChannelById(id))
                .map(channelMapper::toEntity)
                .ifPresent(channelRepository::delete);
    }

    public ChannelDto getChannelById(Long id) {
        return channelRepository.findById(id)
                .map(channelMapper::toDto)
                .orElseThrow(() -> new ChannelNotFoundException("No channel with id=" + id));
    }

    public Optional<ChannelDto> getChannelByName(String name) {
        return channelRepository.findByName(name)
                .map(channelMapper::toDto);
    }
}
