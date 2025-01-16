package paulotech.backend.product.domain.dto;

import paulotech.backend.shared.error.domain.Assert;

public record ProductDescription(String value) {
    public ProductDescription {
        Assert.field("value", value).notNull().minLength(10);
    }
}
