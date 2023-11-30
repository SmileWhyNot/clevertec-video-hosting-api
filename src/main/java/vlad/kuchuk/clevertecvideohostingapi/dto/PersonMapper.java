package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.*;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;

@Mapper
public interface PersonMapper {
    Person toEntity(PersonDto personDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person updateFromDto(PersonDto personDto, @MappingTarget Person person);

    PersonDto toDto(Person person);
}
