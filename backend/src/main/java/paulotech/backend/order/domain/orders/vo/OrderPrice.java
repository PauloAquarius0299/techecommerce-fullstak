package paulotech.backend.order.domain.orders.vo;

import paulotech.backend.shared.error.domain.Assert;

public record OrderPrice(double value) {
    public OrderPrice {
        Assert.field("value", value).strictlyPositive();
    }
}
