package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

@Mapper
public interface ChannelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    Channel toEntity(ChannelDto channelDto);

    ChannelDto toDto(Channel channel);
}
