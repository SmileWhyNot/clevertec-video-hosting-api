package vlad.kuchuk.clevertecvideohostingapi.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vlad.kuchuk.clevertecvideohostingapi.validator.SubscriptionsValidation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SubscriptionsValidation
public class SubscriptionDto {
    @Positive
    Long personId;
    @Positive
    Long channelId;
}
