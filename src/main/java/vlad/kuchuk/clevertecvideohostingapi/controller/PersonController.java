package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.PersonRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.PersonResponse;
import vlad.kuchuk.clevertecvideohostingapi.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}/subscriptions")
    public List<String> getPersonSubscriptionNames(@PathVariable("id") Long id) {
        return personService.getSubscriptionNames(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse registerPerson(
            @RequestBody @Valid PersonRequest personRequest
    ) {
        return personService.savePerson(personRequest);
    }

    @PutMapping("/{id}")
    public PersonResponse updatePerson(
            @RequestBody @Valid PersonRequest updatedPerson,
            @PathVariable("id") Long id
    ) {
        return personService.updatePerson(updatedPerson, id);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(
            @PathVariable("id") Long id
    ) {
        personService.deletePerson(id);
    }
}
