package paulotech.backend.product.infra.secondary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paulotech.backend.product.infra.secondary.entity.CategoryEntity;

import java.util.Optional;
import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {


    Optional<CategoryEntity> findByPublicId(UUID publicId);

    int deleteByPublicId(UUID publicId);
}
