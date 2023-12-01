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

import java.util.HashSet;
import java.util.Objects;
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

    @ManyToMany
    @JoinTable(
        name = "subscriptions",
        joinColumns = { @JoinColumn(name = "person_id") },
        inverseJoinColumns = { @JoinColumn(name = "channel_id") }
    )
    @ToString.Exclude
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(id, person.id) && Objects.equals(nickname, person.nickname) && Objects.equals(name, person.name) && Objects.equals(email, person.email) && Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, name, email, password);
    }
}
