package paulotech.backend.product.domain.repositories;

import org.springframework.data.domain.Page;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.PublicId;

import java.awt.print.Pageable;

public interface CategoryRepository {
    Page<Category> findAll(Pageable pageable);

    int delete(PublicId publicId);

    Category save(Category categoryToCreate);

}
