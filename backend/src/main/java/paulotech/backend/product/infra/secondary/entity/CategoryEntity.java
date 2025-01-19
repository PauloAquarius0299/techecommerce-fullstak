package paulotech.backend.product.infra.secondary.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.CategoryName;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.shared.jpa.AbstractAuditingEntity;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product_category")
@Setter
@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class CategoryEntity extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    @SequenceGenerator(name = "categorySequence", sequenceName = "product_category_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;

    @Builder
    public CategoryEntity(Long id, String name, UUID publicId, Set<ProductEntity> products) {
        this.id = id;
        this.name = name;
        this.publicId = publicId;
        this.products = products;
    }

    public static CategoryEntity from(Category category) {
        CategoryEntity.CategoryEntityBuilder categoryEntityBuilder = CategoryEntity.builder();

        if (category.getDbId()!= null) {
            categoryEntityBuilder.id(category.getDbId());
        }

        return categoryEntityBuilder
                .name(category.getName().value())
                .publicId(category.getPublicId().value())
                .build();
    }

    public static Category to(CategoryEntity category) {
        return Category.builder()
                .dbId(category.getId())
                .name(new CategoryName(category.getName()))
                .publicId(new PublicId(category.getPublicId()))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity that)) return false;
        return Objects.equals(publicId, that.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(publicId);
    }

}
