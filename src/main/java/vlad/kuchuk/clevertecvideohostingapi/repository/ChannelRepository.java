package vlad.kuchuk.clevertecvideohostingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long>, JpaSpecificationExecutor<Channel> {

    Optional<Channel> findByName(String name);
}
