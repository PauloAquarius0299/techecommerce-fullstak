package paulotech.backend.product.infra.secondary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paulotech.backend.product.infra.secondary.entity.PictureEntity;

public interface JpaProductPictureRepository extends JpaRepository<PictureEntity, Long> {
}
