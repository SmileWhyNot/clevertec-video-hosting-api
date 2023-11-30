package vlad.kuchuk.clevertecvideohostingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonDto;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonMapper;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;
import vlad.kuchuk.clevertecvideohostingapi.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.exceptions.PersonOperationException;
import vlad.kuchuk.clevertecvideohostingapi.repository.PersonRepository;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonDto savePerson(PersonDto personDto) {
        return Stream.of(personMapper.toEntity(personDto))
                .map(personRepository::save)
                .map(personMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new PersonOperationException("Failed to create new person account"));
    }

    public PersonDto updatePerson(PersonDto updatedPerson, Long id) {
        return personRepository.findById(id)
                .map(p -> personMapper.updateFromDto(updatedPerson, p))
                .map(personRepository::save)
                .map(personMapper::toDto)
                .orElseThrow(() -> new PersonNotFoundException("No person with id=" + id));
    }

    public void deletePerson(Long id) {
        Optional.of(getPersonById(id))
                .map(personMapper::toEntity)
                .ifPresent(personRepository::delete);
    }

    public PersonDto getPersonById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toDto)
                .orElseThrow(() -> new PersonNotFoundException("No person with id=" + id));
    }

    public Optional<PersonDto> getPersonByEmail(String email) {
        return personRepository.findByEmail(email)
                .map(personMapper::toDto);
    }

    public Optional<PersonDto> getPersonByNickname(String nickname) {
        return personRepository.findByNickname(nickname)
                .map(personMapper::toDto);
    }
}
