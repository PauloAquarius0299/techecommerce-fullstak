package paulotech.backend.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paulotech.backend.order.domain.orders.dto.OrderProductQuantity;
import paulotech.backend.product.domain.aggregates.Category;
import paulotech.backend.product.domain.aggregates.FilterQuery;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.domain.services.CategoryCRUD;
import paulotech.backend.product.domain.services.ProductCRUD;
import paulotech.backend.product.domain.services.ProductShop;
import paulotech.backend.product.domain.services.ProductUpdater;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private ProductCRUD productCRUD;
    private CategoryCRUD categoryCRUD;
    private ProductShop productShop;
    private ProductUpdater  productUpdater;

    @Transactional
    public Product createProduct(Product newProduct){
        return productCRUD.save(newProduct);
    }

    @Transactional(readOnly = true)
    public Page<Product> findAllProduct(Pageable pageable){
        return productCRUD.findAll(pageable);
    }

    @Transactional
    public PublicId deleteProduct(PublicId publicId){
        return productCRUD.delete(publicId);
    }

    @Transactional
    public Category createCategory(Category newCategory){
        return categoryCRUD.save(newCategory);
    }

    @Transactional
    public PublicId deleteCategory(PublicId publicId){
        return categoryCRUD.delete(publicId);
    }

    @Transactional(readOnly = true)
    public Page<Category> findAllCategory(Pageable pageable){
        return categoryCRUD.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getFeaturedProducts(Pageable pageable){
        return productShop.getFeaturedProducts(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findOne(PublicId id){
        return productCRUD.findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> findRelated(Pageable pageable, PublicId productPublicId){
        return productShop.findRelated(pageable, productPublicId);
    }

    @Transactional(readOnly = true)
    public Page<Product> filter(Pageable pageable, FilterQuery query){
        return productShop.filter(pageable, query);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByPublicIdIn(List<PublicId> publicId){
        return productCRUD.findAllByPublicIdIn(publicId);
    }

    @Transactional
    public void updateProductQuantity(List<OrderProductQuantity> orderProductQuantities){
        productUpdater.updateProductQuantity(orderProductQuantities);
    }

}
