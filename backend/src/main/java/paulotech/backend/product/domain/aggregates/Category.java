package paulotech.backend.product.domain.aggregates;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import paulotech.backend.product.domain.dto.CategoryName;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.shared.error.domain.Assert;

import java.util.UUID;

@Builder
@RequiredArgsConstructor
public class Category {

    private final CategoryName name;

    private Long dbId;
    private PublicId publicId;

    private void assertMandatoryFields(CategoryName categoryName) {
        Assert.notNull("name", categoryName);
    }

    private void initDefualtFields() {
        this.publicId = new PublicId(UUID.randomUUID());
    }

    public String getName(){
        return name;
    }

    public Long getDbId() {
        return dbId;
    }

    public UUID getPublicId() {
        return publicId;
    }


}
