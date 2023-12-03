package vlad.kuchuk.clevertecvideohostingapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Optional<Channel> findByName(String name);
    @Query("select c from Channel c " +
            "where (:name is null or c.name = :name) " +
            "and (:lang is null or c.lang = :lang) " +
            "and (:category is null or c.category = :category)")
    Page<Channel> findByNameIgnoreCaseAndLangIgnoreCaseAndCategoryIgnoreCase(
            @Param("name") String name,
            @Param("lang") String lang,
            @Param("category") String category,
            Pageable pageable);

}
