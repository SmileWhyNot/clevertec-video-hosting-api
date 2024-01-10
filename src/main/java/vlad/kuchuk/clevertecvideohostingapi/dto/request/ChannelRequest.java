package vlad.kuchuk.clevertecvideohostingapi.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vlad.kuchuk.clevertecvideohostingapi.validator.ChannelValidation;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ChannelValidation(message = "Channel data not valid")
public class ChannelRequest {
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String description;
    @PastOrPresent
    private ZonedDateTime creationDate;
    private Long authorId;
    @Size(max = 5)
    private String lang;
    @JsonIgnore
    private byte[] avatar;
    @Size(max = 50)
    private String category;
}
