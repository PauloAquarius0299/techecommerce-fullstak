package paulotech.backend.product.domain.aggregates;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import paulotech.backend.product.domain.dto.CategoryName;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.shared.error.domain.Assert;

import java.util.UUID;

@Builder
public class Category {

    private final CategoryName name;

    private Long dbId;
    private PublicId publicId;

    public Category(CategoryName name, Long dbId, PublicId publicId) {
        assertMandatoryFields(name);
        this.name = name;
        this.dbId = dbId;
        this.publicId = publicId;
    }

    private void assertMandatoryFields(CategoryName categoryName) {
        Assert.notNull("name", categoryName);
    }

    public void initDefaultFields() {
        this.publicId = new PublicId(UUID.randomUUID());
    }

    public CategoryName getName() {
        return name;
    }

    public Long getDbId() {
        return dbId;
    }

    public PublicId getPublicId() {
        return publicId;
    }

}
