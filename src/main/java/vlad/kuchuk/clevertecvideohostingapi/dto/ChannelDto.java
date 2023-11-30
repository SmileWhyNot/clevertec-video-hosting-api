package vlad.kuchuk.clevertecvideohostingapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.multipart.MultipartFile;
import vlad.kuchuk.clevertecvideohostingapi.validator.ChannelValidation;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ChannelValidation
public class ChannelDto {
    private Long id;
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String description;
    @PastOrPresent
    private ZonedDateTime creationDate;
    private PersonDto author;
    @Size(max = 5)
    private String lang;
    @JsonIgnore
    private byte[] avatar;
    @Size(max = 50)
    private String category;
}
