package paulotech.backend.product.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.PublicId;
import paulotech.backend.product.domain.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductCRUD {

    private final ProductRepository productRepository;

    public Product save(Product newProduct){
        newProduct.initDefaultFields();
        return productRepository.save(newProduct);
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public PublicId delete(PublicId productId){
        int nbOfRowsDeleted = productRepository.delete(productId);
        if(nbOfRowsDeleted != 1){
            throw new RuntimeException(String.format("Not product deleted with id %s", productId));
        }
        return productId;
    }

    public Optional<Product> findOne(PublicId productId){
        return productRepository.findOne(productId);
    }

    public List<Product> findAllByPublicIdIn(List<PublicId> publicId){
        return productRepository.findByPublicIds(publicId);
    }
}
