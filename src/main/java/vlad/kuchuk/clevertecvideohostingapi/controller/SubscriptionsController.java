package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vlad.kuchuk.clevertecvideohostingapi.dto.SubscriptionDto;
import vlad.kuchuk.clevertecvideohostingapi.service.SubscriptionsService;

@RestController
@RequestMapping("/api/v1/subscriptions")
@AllArgsConstructor
public class SubscriptionsController {

    private final SubscriptionsService subscriptionsService;

    @PostMapping
    public SubscriptionDto subscribeNewChannel(@RequestBody @Valid SubscriptionDto subscriptionDto) {
        return subscriptionsService.subscribe(subscriptionDto);
    }

    @DeleteMapping
    public void unsubscribeChannel(@RequestBody @Valid SubscriptionDto subscriptionDto) {
        subscriptionsService.unsubscribe(subscriptionDto);
    }
}
