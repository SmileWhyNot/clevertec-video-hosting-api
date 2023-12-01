package vlad.kuchuk.clevertecvideohostingapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vlad.kuchuk.clevertecvideohostingapi.validator.PersonValidation;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@PersonValidation(message = "Person data not valid")
public class PersonDto {
    private Long id;
    @Size(max = 64)
    private String nickname;
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String email;
    @Size(max = 50)
    private String password;
    private Set<ChannelDto> subscriptions;
}
