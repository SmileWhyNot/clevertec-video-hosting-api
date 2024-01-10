package vlad.kuchuk.clevertecvideohostingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.ChannelNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PhotoOperationException;
import vlad.kuchuk.clevertecvideohostingapi.dto.ChannelMapper;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.ChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.FilteredPageableChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.FilteredChannelInfoResponse;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.FullChannelInfoResponse;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;
import vlad.kuchuk.clevertecvideohostingapi.repository.ChannelRepository;
import vlad.kuchuk.clevertecvideohostingapi.repository.ChannelSpecifications;
import vlad.kuchuk.clevertecvideohostingapi.repository.PersonRepository;
import vlad.kuchuk.clevertecvideohostingapi.utils.ImageUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelService {

    private static final String NO_RESOURCE_WITH_ID = "No resource with id = %d";
    private final ChannelRepository channelRepository;
    private final PersonRepository personRepository;
    private final ChannelMapper channelMapper;

    @Transactional
    public FullChannelInfoResponse saveChannel(ChannelRequest channelRequest, MultipartFile file) {
        ChannelRequest resultedChannelRequest = handleImageUpload(channelRequest, file);

        return personRepository.findById(resultedChannelRequest.getAuthorId())
                               .map(person -> {
                                   Channel channel = channelMapper.toEntity(resultedChannelRequest);
                                   channel.setAuthor(person);
                                   return channelRepository.save(channel);
                               })
                               .map(channelMapper::toFullInfoDto)
                               .orElseThrow(() -> new PersonNotFoundException(
                                       String.format(NO_RESOURCE_WITH_ID, channelRequest.getAuthorId())));
    }

    private ChannelRequest handleImageUpload(ChannelRequest channelRequest, MultipartFile file) {
        if (Objects.nonNull(file)) {
            try {
                return channelRequest.setAvatar(ImageUtils.compressImage(file.getBytes()));
            } catch (IOException e) {
                throw new PhotoOperationException(e.getMessage());
            }
        }
        return channelRequest;
    }

    @Transactional
    public FullChannelInfoResponse updateChannel(ChannelRequest updatedChannel, Long id, MultipartFile file) {
        ChannelRequest resultedChannelRequest = handleImageUpload(updatedChannel, file);

        return channelRepository.findById(id)
                                .map(p -> channelMapper.updateFromDto(resultedChannelRequest, p))
                                .map(channelRepository::save)
                                .map(channelMapper::toFullInfoDto)
                                .map(response -> response.setAvatar(ImageUtils.decompressImage(response.getAvatar())))
                                .orElseThrow(
                                        () -> new ChannelNotFoundException(String.format(NO_RESOURCE_WITH_ID, id)));
    }

    @Transactional
    public void deleteChannel(Long id) {
        channelRepository.deleteById(id);
    }

    public Optional<FullChannelInfoResponse> getChannelByName(String name) {
        return channelRepository.findByName(name)
                                .map(channelMapper::toFullInfoDto);
    }

    public FullChannelInfoResponse getFullChannelInfo(Long id) {
        FullChannelInfoResponse fullChannelInfo = channelRepository.findById(id)
                                                                   .map(channelMapper::toFullInfoDto)
                                                                   .orElseThrow(() -> new PersonNotFoundException(
                                                                           String.format(NO_RESOURCE_WITH_ID, id)));

        return fullChannelInfo.setAvatar(ImageUtils.decompressImage(fullChannelInfo.getAvatar()));
    }

    public Page<FilteredChannelInfoResponse> getAllChannelsSortedPageable(FilteredPageableChannelRequest request) {
        Specification<Channel> specification = ChannelSpecifications.filterChannels(
                request.getName(),
                request.getLang(),
                request.getCategory()
        );
        return channelRepository.findAll(specification, request.toPageable())
                                .map(channelMapper::toFilteredChannelInfoDto);
    }
}
