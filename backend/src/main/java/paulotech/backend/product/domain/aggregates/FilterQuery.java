package paulotech.backend.product.domain.aggregates;


import lombok.Builder;
import paulotech.backend.product.domain.dto.ProductSize;
import paulotech.backend.product.domain.dto.PublicId;

import java.util.List;

@Builder
public record FilterQuery(PublicId categoryId, List<ProductSize> sizes) {
}
