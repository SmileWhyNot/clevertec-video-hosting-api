package vlad.kuchuk.clevertecvideohostingapi.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vlad.kuchuk.clevertecvideohostingapi.validator.PersonValidation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@PersonValidation(message = "Person data not valid")
public class PersonRequest {
    @Size(max = 64)
    private String nickname;
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String email;
    @Size(max = 50)
    private String password;
}
