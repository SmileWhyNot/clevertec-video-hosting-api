package vlad.kuchuk.clevertecvideohostingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vlad.kuchuk.clevertecvideohostingapi.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.subscriptions WHERE p.id = :id")
    Optional<Person> findPersonWithSubscriptionsById(@Param("id") Long id);
    Optional<Person> findByNickname(String nickname);
}
