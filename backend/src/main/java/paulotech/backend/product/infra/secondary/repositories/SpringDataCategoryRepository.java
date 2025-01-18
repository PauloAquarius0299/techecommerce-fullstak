package paulotech.backend.product.infra.secondary.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.domain.repositories.CategoryRepository;
import paulotech.backend.product.infra.secondary.entity.CategoryEntity;

@Repository
@RequiredArgsConstructor
public abstract class SpringDataCategoryRepository implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    public Page<Category> findAll(Pageable pageable) {
       return jpaCategoryRepository.findAll(pageable).map(CategoryEntity::to);
    }

    @Override
    public int delete(PublicId publicId){
        return jpaCategoryRepository.deleteByPublicId(publicId.value());
    }

    @Override
    public Category save(Category categoryToCreate) {
        CategoryEntity categoryToSave = CategoryEntity.from(categoryToCreate);
        CategoryEntity savedCategory = jpaCategoryRepository.save(categoryToSave);
        return CategoryEntity.to(savedCategory);
    }
}
