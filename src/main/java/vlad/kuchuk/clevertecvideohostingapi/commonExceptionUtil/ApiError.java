package vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String errorType;
    private String message;
}
