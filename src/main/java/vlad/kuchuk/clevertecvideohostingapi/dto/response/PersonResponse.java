package vlad.kuchuk.clevertecvideohostingapi.dto.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.ChannelRequest;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PersonResponse {
    private Long id;
    @Size(max = 64)
    private String nickname;
    @Size(max = 64)
    private String name;
    @Size(max = 100)
    private String email;
    @Size(max = 50)
    private String password;
    private Set<ChannelRequest> subscriptions;
}
