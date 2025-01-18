package paulotech.backend.product.infra.secondary.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paulotech.backend.product.domain.dto.ProductSize;
import paulotech.backend.product.infra.secondary.entity.PictureEntity;
import paulotech.backend.product.infra.secondary.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    int deleteByPublicId(UUID publicId);

    Optional<ProductEntity> findByPublicId(UUID publicId);

    Page<ProductEntity> findAllByFeaturedTrue(Pageable pageable);

    Page<ProductEntity> findAllByCategoryPublicIdAndPublicIdNot(Pageable pageable, UUID categoryPublicId, UUID excludedProductPublicId);

    @Query("SELECT product FROM ProductEntity product WHERE (:sizes is null or product.size IN (:sizes)) AND " +
            "product.category.publicId = :categoryPublicId")
    Page<ProductEntity> findAllByCategoryPublicIdAndSizesIn(Pageable pageable, UUID categoryPublicId, List<ProductSize> sizes);

    List<ProductEntity> findAllByPublicIdIn(List<UUID> publicIds);

    @Modifying
    @Query("UPDATE ProductEntity product " +
            "SET product.nbInStock = product.nbInStock - :quantity " +
            "WHERE product.publicId = :productPublicId")
    void updateQuantity(@Param("productPublicId") UUID productPublicId, @Param("quantity") long quantity);

    @Modifying
    @Query("UPDATE PictureEntity p SET p.product = :product WHERE p IN :pictures")
    void saveAllPictures(@Param("pictures") List<PictureEntity> pictures, @Param("product") ProductEntity product);
}