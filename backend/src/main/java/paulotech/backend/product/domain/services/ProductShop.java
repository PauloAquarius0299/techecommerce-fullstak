package paulotech.backend.product.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import paulotech.backend.product.domain.aggregates.FilterQuery;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.domain.repositories.ProductRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductShop {

    private ProductRepository productRepository;

    public Page<Product> getFeaturedProducts(Pageable pegeable){
        return productRepository.findAllFeaturedProduct(pegeable);
    }

    public Page<Product> findRelated(Pageable pageable, PublicId productPublicId){
        Optional<Product> productOpt = productRepository.findOne(productPublicId);
        if(productOpt.isPresent()){
            Product product = productOpt.get();
            return productRepository.findAllByCategoryExcludingOne(pageable,
                    product.getCategory().getPublicId(), productPublicId);
        }else {
            throw new EntityNotFoundException(String.format("No product found with id %s", productPublicId));
        }
    }

    public Page<Product> filter(Pageable pageable, FilterQuery query){
        return productRepository.findByCategoryAndSize(pageable, query);
    }
}
