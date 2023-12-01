package vlad.kuchuk.clevertecvideohostingapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person author;

    @ManyToMany(mappedBy = "subscriptions")
    @ToString.Exclude
    private Set<Person> subscribers;

    @Column(unique = true)
    @Size(max = 64)
    @NotNull
    private String name;

    @Size(max = 100)
    private String description;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    private ZonedDateTime creationDate;

    @NotNull
    @Size(max = 5)
    @ColumnDefault("en")
    private String lang;

    private byte[] avatar;

    @Size(max = 50)
    private String category;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Channel channel = (Channel) object;
        return Objects.equals(id, channel.id) && Objects.equals(name, channel.name) && Objects.equals(description, channel.description) && Objects.equals(creationDate, channel.creationDate) && Objects.equals(lang, channel.lang) && Arrays.equals(avatar, channel.avatar) && Objects.equals(category, channel.category);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, creationDate, lang, category);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }
}
