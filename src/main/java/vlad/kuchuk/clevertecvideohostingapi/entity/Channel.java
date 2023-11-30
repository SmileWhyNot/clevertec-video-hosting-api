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
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.HashSet;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person author;

    @ManyToMany
    @JoinTable(
            name = "subscriptions",
            joinColumns = { @JoinColumn(name = "channel_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") }
    )
    @ToString.Exclude
    @EqualsExclude
    @HashCodeExclude
    private Set<Person> subscribers = new HashSet<>();

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
}
