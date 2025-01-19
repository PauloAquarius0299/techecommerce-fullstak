package paulotech.backend.product.infra.secondary.repositories;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import paulotech.backend.product.domain.aggregates.Picture;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.domain.repositories.ProductRepository;
import paulotech.backend.product.infra.secondary.entity.CategoryEntity;
import paulotech.backend.product.infra.secondary.entity.PictureEntity;
import paulotech.backend.product.infra.secondary.entity.ProductEntity;

import java.util.*;

@Repository
@RequiredArgsConstructor
public abstract class SpringDataProductRepository implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaProductPictureRepository jpaProductPictureRepository;

    public Product save(Product productToCreate) {
        ProductEntity newProductEntity = ProductEntity.from(productToCreate);
        Optional<CategoryEntity> categoryEntityOpt = jpaCategoryRepository.findByPublicId(newProductEntity.getCategory().getPublicId());
        CategoryEntity categoryEntity = categoryEntityOpt.orElseThrow(
                () -> new EntityNotFoundException(String.format("No category found with Id %s", productToCreate.getCategory().getPublicId()))
        );

        newProductEntity.setCategory(categoryEntity);
        ProductEntity savedProductEntity = jpaProductRepository.save(newProductEntity);
        Set<PictureEntity> pictureEntities = PictureEntity.from(productToCreate.getPictures());
        pictureEntities.forEach(pictureEntity -> pictureEntity.setProduct(savedProductEntity));

        jpaProductRepository.saveAllPictures(new ArrayList<>(pictureEntities), savedProductEntity);
        savedProductEntity.setPictures(pictureEntities);

        return ProductEntity.to(savedProductEntity);
    }

    private void saveAllPictures(List<Picture> pictures, ProductEntity newProductEntity) {
        Set<PictureEntity> picturesEntities = PictureEntity.from(pictures);

        for (PictureEntity picturesEntity : picturesEntities) {
            picturesEntity.setProduct(newProductEntity);
        }

        jpaProductPictureRepository.saveAll(picturesEntities);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return jpaProductRepository.findAll(pageable).map(ProductEntity::to);
    }

    @Override
    public int delete(PublicId publicId) {
        return jpaProductRepository.deleteByPublicId(publicId.value());
    }

    @Override
    public Page<Product> findAllFeaturedProducts(Pageable pageable) {
        return jpaProductRepository.findAllByFeaturedTrue(pageable).map(ProductEntity::to);
    }

    @Override
    public Optional<Product> findByOne(PublicId publicId) {
        return jpaProductRepository.findByPublicId(publicId.value()).map(ProductEntity::to);
    }

    @Override
    public Page<Product> findByCategoryExcludingOne(Pageable pageable, PublicId categoryPublicId, PublicId productPublicId) {
        return jpaProductRepository.findAllByCategoryPublicIdAndPublicIdNot(
                pageable, categoryPublicId.value(), productPublicId.value()
        ).map(ProductEntity::to);
    }

    @Override
    public List<Product> findByPublicIds(List<PublicId> publicIds) {
        List<UUID> publicIdsUUID = publicIds.stream().map(PublicId::value).toList();
        return jpaProductRepository.findAllByPublicIdIn(publicIdsUUID).stream().map(ProductEntity::to).toList();
    }

    @Override
    public void updateQuantity(PublicId productPublicId, long quantity) {
        jpaProductRepository.updateQuantity(productPublicId.value(), quantity);
    }
}
