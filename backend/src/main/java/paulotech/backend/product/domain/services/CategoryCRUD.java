package paulotech.backend.product.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.domain.repositories.CategoryRepository;

@RequiredArgsConstructor
public class CategoryCRUD {
    private final CategoryRepository categoryRepository;

    public Category save(Category category){
        category.initDefaultFields();
        return categoryRepository.save(category);
    }

    public Page<Category> findAll(Pageable pageable){
        return categoryRepository.findAll((java.awt.print.Pageable) pageable);
    }

    public PublicId delete(PublicId categoryId){
        int nbOfRowsDeleted = categoryRepository.delete(categoryId);
        if(nbOfRowsDeleted != 1){
            throw new EntityNotFoundException(String.format("Not category deleted with id %s", categoryId));
        }
        return categoryId;
    }
}
