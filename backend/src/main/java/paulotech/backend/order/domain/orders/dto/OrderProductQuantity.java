package paulotech.backend.order.domain.orders.dto;

import paulotech.backend.order.domain.orders.vo.OrderQuantity;
import paulotech.backend.order.domain.orders.vo.ProductPublicId;

public record OrderProductQuantity(OrderQuantity quantity, ProductPublicId productPublicId) {
}
