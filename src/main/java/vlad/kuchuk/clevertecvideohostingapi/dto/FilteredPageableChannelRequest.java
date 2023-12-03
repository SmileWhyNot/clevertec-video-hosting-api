package vlad.kuchuk.clevertecvideohostingapi.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FilteredPageableChannelRequest {

    @Size(max = 64)
    private String name;
    @Size(max = 5)
    private String lang;
    @Size(max = 50)
    private String category;
    @PositiveOrZero
    private Integer page = 1;
    @Positive
    private Integer size = 5;

    public void setLang(String lang) {
        this.lang = (lang == null) ? null : lang.toLowerCase();
    }

    public void setCategory(String category) {
        this.category = (category == null) ? null : category.toLowerCase();
    }

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
