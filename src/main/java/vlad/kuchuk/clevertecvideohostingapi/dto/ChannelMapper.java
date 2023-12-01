package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.*;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

@Mapper
public interface ChannelMapper {

    Channel toEntity(ChannelDto channelDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", ignore = true)
    Channel updateFromDto(ChannelDto channelDto, @MappingTarget Channel channel);

    ChannelDto toDto(Channel channel);
    @Mapping(target = "avatar", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subscribersCount", expression = "java(channel.getSubscribers().size())")
    FullChannelInfoDto toFullInfoDto(Channel channel);

}
