package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.*;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;

@Mapper
public interface PersonMapper {
    @Mapping(target = "subscriptions", source = "subscriptions")
    Person toEntity(PersonDto personDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person updateFromDto(PersonDto personDto, @MappingTarget Person person);
    @Mapping(target = "subscriptions", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PersonDto toDto(Person person);
}
