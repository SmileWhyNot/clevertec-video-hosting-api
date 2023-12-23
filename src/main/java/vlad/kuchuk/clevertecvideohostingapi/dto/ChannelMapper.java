package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.*;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.ChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.FilteredChannelInfoResponse;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.FullChannelInfoResponse;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

@Mapper
public interface ChannelMapper {

    @BeforeMapping
    default void convertToLowerCase(ChannelRequest channelRequest, @MappingTarget Channel channel) {
        if (channelRequest.getCategory() != null) {
            channel.setCategory(channelRequest.getCategory().toLowerCase());
        }
        if (channelRequest.getLang() != null) {
            channel.setLang(channelRequest.getLang().toLowerCase());
        }
    }

    @Mapping(target = "lang", ignore = true)
    @Mapping(target = "category", ignore = true)
    Channel toEntity(ChannelRequest channelRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "lang", ignore = true)
    @Mapping(target = "category", ignore = true)
    Channel updateFromDto(ChannelRequest channelRequest, @MappingTarget Channel channel);

    @Mapping(target = "avatar", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subscribersCount", expression = "java((channel.getSubscribers() != null) ? channel.getSubscribers().size() : 0)")
    FullChannelInfoResponse toFullInfoDto(Channel channel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subscribersCount", expression = "java(channel.getSubscribers().size())")
    FilteredChannelInfoResponse toFilteredChannelInfoDto(Channel channel);
}
