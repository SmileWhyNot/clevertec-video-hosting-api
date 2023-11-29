package vlad.kuchuk.clevertecvideohostingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChannelDto {
    private String name;
    private String description;
    private ZonedDateTime creationDate;
    private String lang;
    private byte[] avatar;
    private String category;
}
