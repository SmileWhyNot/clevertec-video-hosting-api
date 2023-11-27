package vlad.kuchuk.clevertecvideohostingapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "author", cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private Channel channel;

    @ManyToMany
    @JoinTable(
        name = "subscriptions",
        joinColumns = { @JoinColumn(name = "person_id") },
        inverseJoinColumns = { @JoinColumn(name = "channel_id") }
    )
    private Set<Channel> subscriptions;

    @Size(max = 64)
    @NotNull
    private String nickname;

    @Size(max = 64)
    @NotNull
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(unique = true)
    private String email;

    @Size(max = 64)
    @NotNull
    private String password;
}
