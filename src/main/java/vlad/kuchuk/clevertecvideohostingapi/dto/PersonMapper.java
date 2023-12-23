package vlad.kuchuk.clevertecvideohostingapi.dto;

import org.mapstruct.*;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.PersonRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.PersonResponse;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;

@Mapper
public interface PersonMapper {
    Person toEntity(PersonRequest personRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person updateFromDto(PersonRequest personRequest, @MappingTarget Person person);
    @Mapping(target = "subscriptions", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PersonResponse toDto(Person person);
}
