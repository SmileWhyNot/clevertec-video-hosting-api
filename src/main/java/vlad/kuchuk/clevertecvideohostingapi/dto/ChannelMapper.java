package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.*;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

@Mapper
public interface ChannelMapper {

    @BeforeMapping
    default void convertToLowerCase(ChannelDto channelDto, @MappingTarget Channel channel) {
        if (channelDto.getCategory() != null) {
            channel.setCategory(channelDto.getCategory().toLowerCase());
        }
        if (channelDto.getLang() != null) {
            channel.setLang(channelDto.getLang().toLowerCase());
        }
    }

    @Mapping(target = "lang", ignore = true)
    @Mapping(target = "category", ignore = true)
    Channel toEntity(ChannelDto channelDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "lang", ignore = true)
    @Mapping(target = "category", ignore = true)
    Channel updateFromDto(ChannelDto channelDto, @MappingTarget Channel channel);

    ChannelDto toDto(Channel channel);
    @Mapping(target = "avatar", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subscribersCount", expression = "java(channel.getSubscribers().size())")
    FullChannelInfoDto toFullInfoDto(Channel channel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subscribersCount", expression = "java(channel.getSubscribers().size())")
    FilteredChannelInfoDto toFilteredChannelInfoDto(Channel channel);
}
