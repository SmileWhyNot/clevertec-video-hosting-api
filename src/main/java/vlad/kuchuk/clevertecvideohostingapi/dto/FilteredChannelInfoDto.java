package vlad.kuchuk.clevertecvideohostingapi.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FilteredChannelInfoDto {
    private Long id;
    @Size(max = 64)
    private String name;
    private Integer subscribersCount;
    @Size(max = 5)
    private String lang;
    private byte[] avatar;
    @Size(max = 50)
    private String category;
}
