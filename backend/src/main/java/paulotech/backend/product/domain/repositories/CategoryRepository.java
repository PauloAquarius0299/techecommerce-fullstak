package paulotech.backend.product.domain.repositories;

import org.springframework.data.domain.Page;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.infra.secondary.entity.CategoryEntity;

import java.awt.print.Pageable;

public interface CategoryRepository {
    Page<Category> findAll(Pageable pageable);

    int delete(PublicId publicId);

    CategoryEntity save(Category categoryToCreate);

}
