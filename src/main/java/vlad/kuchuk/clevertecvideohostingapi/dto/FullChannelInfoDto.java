package vlad.kuchuk.clevertecvideohostingapi.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FullChannelInfoDto {

    private Long id;
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String description;
    @PastOrPresent
    private ZonedDateTime creationDate;
    private PersonDto author;
    private Integer subscribersCount;
    @Size(max = 5)
    private String lang;
    private byte[] avatar;
    @Size(max = 50)
    private String category;
}
