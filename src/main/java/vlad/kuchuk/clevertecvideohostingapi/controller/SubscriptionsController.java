package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlad.kuchuk.clevertecvideohostingapi.dto.SubscriptionDto;
import vlad.kuchuk.clevertecvideohostingapi.service.SubscriptionsService;

@RestController
@RequestMapping("/api/v1/subscriptions")
@AllArgsConstructor
public class SubscriptionsController {

    private final SubscriptionsService subscriptionsService;

    @PostMapping("/subscribe")
    public ResponseEntity<SubscriptionDto> subscribeNewChannel(@RequestBody @Valid SubscriptionDto subscriptionDto) {
        return ResponseEntity.ok(subscriptionsService.subscribe(subscriptionDto));
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeChannel(@RequestBody @Valid SubscriptionDto subscriptionDto) {
        subscriptionsService.unsubscribe(subscriptionDto);
    }
}
