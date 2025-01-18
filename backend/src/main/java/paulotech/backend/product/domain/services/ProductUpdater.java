package paulotech.backend.product.domain.services;

import lombok.RequiredArgsConstructor;
import paulotech.backend.order.domain.orders.dto.OrderProductQuantity;
import paulotech.backend.product.domain.repositories.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
public class ProductUpdater {

    private final ProductRepository productRepository;

    public void updateProductQuantity(List<OrderProductQuantity> orderProductQnt){
        for(OrderProductQuantity orderProductQuantity : orderProductQnt){
            productRepository.updateQuantity(orderProductQuantity.productPublicId(),
                    orderProductQuantity.quantity().value());
        }
    }
}
