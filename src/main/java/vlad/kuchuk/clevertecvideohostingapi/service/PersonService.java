package vlad.kuchuk.clevertecvideohostingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonNotFoundException;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PersonOperationException;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.PersonRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonMapper;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.PersonResponse;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;
import vlad.kuchuk.clevertecvideohostingapi.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Transactional
    public PersonResponse savePerson(PersonRequest personRequest) {
        return Stream.of(personMapper.toEntity(personRequest))
                .map(personRepository::save)
                .map(personMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new PersonOperationException("Failed to create new person account"));
    }

    @Transactional
    public PersonResponse updatePerson(PersonRequest updatedPerson, Long id) {
        return personRepository.findById(id)
                .map(p -> personMapper.updateFromDto(updatedPerson, p))
                .map(personRepository::save)
                .map(personMapper::toDto)
                .orElseThrow(() -> new PersonNotFoundException("No person with id=" + id));
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<PersonResponse> getPersonByEmail(String email) {
        return personRepository.findByEmail(email)
                .map(personMapper::toDto);
    }

    public Optional<PersonResponse> getPersonByNickname(String nickname) {
        return personRepository.findByNickname(nickname)
                .map(personMapper::toDto);
    }

    public List<String> getSubscriptionNames(Long id) {
        return personRepository.findById(id).stream()
                .flatMap(p -> p.getSubscriptions().stream())
                .map(Channel::getName)
                .toList();
    }
}
