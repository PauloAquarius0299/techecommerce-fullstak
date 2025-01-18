package paulotech.backend.order.domain.orders.vo;

import paulotech.backend.shared.error.domain.Assert;

import java.util.UUID;

public record ProductPublicId(UUID value) {
    public ProductPublicId {
        Assert.notNull("value", value);
    }
}
