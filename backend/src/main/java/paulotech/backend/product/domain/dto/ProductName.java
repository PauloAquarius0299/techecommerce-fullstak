package paulotech.backend.product.domain.dto;

import paulotech.backend.shared.error.domain.Assert;

public record ProductName(String value) {
    public ProductName {
        Assert.notNull("value", value);
        Assert.field("value", value).minLength(3).maxLength(256);
    }
}
