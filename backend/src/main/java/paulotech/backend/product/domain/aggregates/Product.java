package paulotech.backend.product.domain.aggregates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import paulotech.backend.product.domain.dto.*;
import paulotech.backend.product.infra.secondary.entity.CategoryEntity;
import paulotech.backend.product.infra.secondary.entity.PictureEntity;
import paulotech.backend.shared.error.domain.Assert;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Product {

    private final ProductBrand productBrand;
    private final ProductName productName;
    private final ProductColor  productColor;
    private final ProductDescription  productDescription;
    private final ProductPrice productPrice;
    private final ProductSize productSize;
    private final Category category;
    private final List<Picture> pictures;
    private Long dbId;
    private boolean featured;
    private PublicId publicId;
    private int nbInStock;

    public Product(ProductBrand productBrand, ProductName productName, ProductColor productColor,
                   ProductDescription productDescription, ProductPrice productPrice, ProductSize productSize,
                   Category category, List<Picture> pictures) {
        this.productBrand = productBrand;
        this.productName = productName;
        this.productColor = productColor;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.category = category;
        this.pictures = pictures;
    }

    private void assertMandatoryFields(ProductBrand productBrand, ProductName productName, ProductColor productColor, ProductDescription productDescription, ProductPrice productPrice, ProductSize productSize, Category category, List<Picture> pictures) {
        Assert.notNull("productBrand", productBrand);
        Assert.notNull("productName", productName);
        Assert.notNull("productColor", productColor);
        Assert.notNull("productDescription", productDescription);
        Assert.notNull("productPrice", productPrice);
        Assert.notNull("productSize", productSize);
        Assert.notNull("category", category);
        Assert.notNull("pictures", pictures);
    }

    public void initDefaultFields() {
        this.publicId = new PublicId(UUID.randomUUID());
    }

}
