package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlad.kuchuk.clevertecvideohostingapi.dto.PersonDto;
import vlad.kuchuk.clevertecvideohostingapi.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}/subscriptions")
    public List<String> getPersonSubscriptionNames(@PathVariable("id") Long id) {
        return personService.getSubscriptionNames(id);
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> registerPerson(
            @RequestBody @Valid PersonDto personDto
    ) {
        PersonDto createdPersonDto = personService.savePerson(personDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdPersonDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(
            @RequestBody @Valid PersonDto updatedPerson,
            @PathVariable("id") Long id
    ) {
        PersonDto updatedPersonDto = personService.updatePerson(updatedPerson, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedPersonDto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(
            @PathVariable("id") Long id
    ) {
        personService.deletePerson(id);
    }
}
