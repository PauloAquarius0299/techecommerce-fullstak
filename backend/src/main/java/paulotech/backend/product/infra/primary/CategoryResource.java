package paulotech.backend.product.infra.primary;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import paulotech.backend.product.application.ProductApplicationService;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.PublicId;

import java.util.UUID;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private static final Logger log = LoggerFactory.getLogger(CategoryResource.class);
    private final ProductApplicationService productApplicationService;

    @PostMapping
    public ResponseEntity<RestCategory> save(@RequestBody RestCategory category) {
        Category categoryDomain = RestCategory.toDomain(category);
        Category categorySaved = productApplicationService.createCategory(categoryDomain);
        return ResponseEntity.ok(RestCategory.fromDomain(categorySaved));
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<?> delete(@PathVariable UUID publicId) {
        try {
            PublicId deletedCategoryPublicId = productApplicationService.deleteCategory(new PublicId(publicId));
            return ResponseEntity.ok(deletedCategoryPublicId.value());
        } catch (EntityNotFoundException enff) {
            log.error("Could not delete category with id {}", publicId, enff);
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Category not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
        }
    }

    @GetMapping
    public ResponseEntity<Page<RestCategory>> findAll(Pageable pageable) {
        Page<Category> categories = productApplicationService.findAllCategory(pageable);
        PageImpl<RestCategory> restCategories = new PageImpl<>(
                categories.map(RestCategory::fromDomain).toList(),
                pageable,
                categories.getTotalElements()
        );
        return ResponseEntity.ok(restCategories);
    }
}
