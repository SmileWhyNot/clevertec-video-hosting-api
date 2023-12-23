package vlad.kuchuk.clevertecvideohostingapi.repository;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import vlad.kuchuk.clevertecvideohostingapi.entity.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ChannelSpecifications {

    public static Specification<Channel> filterChannels(String name, String lang, String category) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(name)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("name"), value)));

            Optional.ofNullable(lang)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("lang"), value)));

            Optional.ofNullable(category)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("category"), value)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

