package paulotech.backend.product.domain.dto;

import paulotech.backend.shared.error.domain.Assert;

public record ProductPrice(Double value) {
    public ProductPrice {
        Assert.field("value", value).min(0.1);
    }
}
