package paulotech.backend.product.infra.primary;

import lombok.Builder;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.dto.CategoryName;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.shared.error.domain.Assert;

import java.util.UUID;

@Builder
public record RestCategory(UUID publicId,
                           String name) {
    public RestCategory {
        Assert.notNull("name", name);
    }

    public static Category toDomain(RestCategory restCategory){
        return Category.builder()
                .name(restCategory.name != null ? new CategoryName(restCategory.name) : null)
                .publicId(restCategory.publicId != null ? new PublicId(restCategory.publicId): null)
                .build();
    }

    public static RestCategory fromDomain(Category category){
        return RestCategory.builder()
                .publicId(category.getPublicId().value())
                .name(category.getName().value())
                .build();
    }
}
