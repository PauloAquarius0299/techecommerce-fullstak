package paulotech.backend.product.domain.dto;

import paulotech.backend.shared.error.domain.Assert;

public record CategoryName(String value) {
    public CategoryName {
        Assert.field("value", value).notNull().minLength(3);
    }
}
