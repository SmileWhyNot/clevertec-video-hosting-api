package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;

@Mapper
public interface PersonMapper {
    Person toEntity(PersonDto personDto);

    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "nickname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person updateFromDto(PersonDto personDto, @MappingTarget Person person);
    PersonDto toDto(Person person);
}
