package vlad.kuchuk.clevertecvideohostingapi.dto.response;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.PersonRequest;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FullChannelInfoResponse {

    private Long id;
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String description;
    @PastOrPresent
    private ZonedDateTime creationDate;
    private PersonRequest author;
    private Integer subscribersCount;
    @Size(max = 5)
    private String lang;
    private byte[] avatar;
    @Size(max = 50)
    private String category;
}
