package paulotech.backend.order.domain.orders.vo;

import paulotech.backend.shared.error.domain.Assert;

public record OrderQuantity(long value) {
    public OrderQuantity {
        Assert.field("value", value).positive();

    }
}
