package paulotech.backend.product.infra.secondary.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.shared.jpa.AbstractAuditingEntity;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product_category")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
public class CategoryEntity extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    @SequenceGenerator(name = "categorySequence", sequenceName = "product_category_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;

    public CategoryEntity(String name, UUID publicId, Set<ProductEntity> products) {
        this.name = name;
        this.publicId = publicId;
        this.products = products;
    }

    public static CategoryEntity from(Category category){
        CategoryEntity.CategoryEntityBuilder categoryEntityBuilder = CategoryEntity.builder();

        if (category.getDbId() != null) {
            categoryEntityBuilder.id(category.getDbId());
        }

        return CategoryEntity.builder()
                .name(category.getName().toString())
                .publicId(category.getPublicId().value())
                .build();
    }

    public static CategoryEntity to(CategoryEntity categoryEntity){
        return CategoryEntity.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .publicId(categoryEntity.getPublicId())
                .build();
    }

}
