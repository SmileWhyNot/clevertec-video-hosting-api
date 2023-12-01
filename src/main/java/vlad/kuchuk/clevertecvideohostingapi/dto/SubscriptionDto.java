package vlad.kuchuk.clevertecvideohostingapi.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SubscriptionDto {
    @Positive
    Long personId;
    @Positive
    Long channelId;
}
