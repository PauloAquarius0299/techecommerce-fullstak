package paulotech.backend.product.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import paulotech.backend.order.domain.orders.vo.ProductPublicId;
import paulotech.backend.product.domain.aggregates.FilterQuery;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.PublicId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product productToCreate);

    Page<Product> findAll(Pageable pageable);

    int delete(PublicId publicId);

    Page<Product> findAllFeaturedProduct(Pageable pageable);

    Optional<Product> findOne(PublicId publicId);

    Page<Product> findAllByCategoryExcludingOne(Pageable pageable, PublicId categoryPublicId, PublicId productPublicId);

    Page<Product> findByCategoryAndSize(Pageable pageable, FilterQuery filterQuery);

    Page<Product> findAllFeaturedProducts(Pageable pageable);

    Optional<Product> findByOne(PublicId publicId);

    Page<Product> findByCategoryExcludingOne(Pageable pageable, PublicId categoryPublicId, PublicId productPublicId);

    List<Product> findByPublicIds(List<PublicId> publicIds);

    void updateQuantity(ProductPublicId productPublicId, long quantity);
}
