package vlad.kuchuk.clevertecvideohostingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelOperationException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.dto.*;
import vlad.kuchuk.clevertecvideohostingapi.repository.ChannelRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final PersonService personService;
    private final ChannelMapper channelMapper;
    private final PersonMapper personMapper;

    @Transactional
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

    @Transactional
    public FullChannelInfoDto updateChannel(ChannelDto updatedChannel, Long id) {
        return channelRepository.findById(id)
                .map(p -> channelMapper.updateFromDto(updatedChannel, p))
                .map(channelRepository::save)
                .map(channelMapper::toFullInfoDto)
                .orElseThrow(() -> new ChannelNotFoundException("No channel with id=" + id));
    }

    @Transactional
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

    public FullChannelInfoDto getFullChannelInfo(Long id) {
         return channelRepository.findById(id)
                 .map(channelMapper::toFullInfoDto)
                 .orElseThrow(() -> new PersonNotFoundException("No channel with id=" + id));
    }

    public Page<FilteredChannelInfoDto> getAllChannelsSortedPageable(FilteredPageableChannelRequest request) {
        return channelRepository.findByNameLangCategoryPageable(
                request.getName(),
                request.getLang(),
                request.getCategory(),
                request.toPageable()
        ).map(channelMapper::toFilteredChannelInfoDto);
    }
}
