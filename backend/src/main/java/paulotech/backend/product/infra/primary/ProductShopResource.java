package paulotech.backend.product.infra.primary;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paulotech.backend.product.application.ProductApplicationService;
import paulotech.backend.product.domain.aggregates.FilterQuery;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.ProductSize;
import paulotech.backend.product.domain.dto.PublicId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/products-shop")
@RequiredArgsConstructor
public class ProductShopResource {

    private final ProductApplicationService productApplicationService;

    @GetMapping("/featured")
    public ResponseEntity<Page<RestProduct>> getAllFeatured(Pageable pageable){
        Page<Product> products = productApplicationService.getFeaturedProducts(pageable);

        PageImpl<RestProduct> restProducts = new PageImpl<>(
                products.map(RestProduct::fromDomain).toList(),
                pageable,
                products.getTotalElements()
        );
        return ResponseEntity.ok(restProducts);
    }

    @GetMapping("/find-one")
    public ResponseEntity<RestProduct> getOne(@RequestParam("publicId")UUID id){
        Optional<Product> productOpt = productApplicationService.findOne(new PublicId(id));

        return productOpt.map(product -> ResponseEntity.ok(RestProduct.fromDomain(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/related")
    public ResponseEntity<Page<RestProduct>> findRelated(Pageable pageable,
                                                         @RequestParam("publicId")UUID id){
        try{
            Page<Product> products = productApplicationService.findRelated(pageable, new PublicId(id));
            PageImpl<RestProduct> restProducts = new PageImpl<>(
                    products.getContent().stream().map(RestProduct::fromDomain).toList(),
                    pageable,
                    products.getTotalElements()
            );
            return ResponseEntity.ok(restProducts);
        }catch (EntityNotFoundException enfee){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<RestProduct>> filter(Pageable pageable,
                                                    @RequestParam("categoryId") UUID categoryId,
                                                    @RequestParam(value = "productSize", required = false)List<ProductSize> productSizes){
        FilterQuery.FilterQueryBuilder filterQueryBuilder = FilterQuery.builder()
                .categoryId(new PublicId(categoryId));
        if(productSizes != null){
            filterQueryBuilder.sizes(productSizes);
        }
        Page<Product> products = productApplicationService.filter(pageable, filterQueryBuilder.build());
        PageImpl<RestProduct> restProducts = new PageImpl<>(
                products.getContent().stream().map(RestProduct::fromDomain).toList(),
                pageable,
                products.getTotalElements()
        );
        return ResponseEntity.ok(restProducts);
    }
}
