package vlad.kuchuk.clevertecvideohostingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    Optional<Person> findByNickname(String nickname);
    @Query("SELECT p.subscriptions FROM Person p WHERE p.id = :personId")
    Set<Channel> findSubscribedChannelsById(@Param("personId") Long personId);
}
