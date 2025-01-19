package paulotech.backend.product.infra.primary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class RestProduct{

    private String brand;
    private String color;
    private String description;
    private String name;
    private double price;
    private ProductSize size;
    private RestCategory category;
    private boolean featured;
    private List<RestPicture> pictures;
    private UUID publicId;
    private int nbInStock;

    public RestProduct(){
    }

    public RestProduct(String brand, String color, String description, String name, double price, ProductSize size, RestCategory category, boolean featured, List<RestPicture> pictures, UUID publicId, int nbInStock) {
        this.brand = brand;
        this.color = color;
        this.description = description;
        this.name = name;
        this.price = price;
        this.size = size;
        this.category = category;
        this.featured = featured;
        this.pictures = pictures;
        this.publicId = publicId;
        this.nbInStock = nbInStock;
    }

    public void addPictureAttachment(List<RestPicture> pictures){
        this.pictures.addAll(pictures);
    }

    public static Product toDomain(RestProduct restProduct){
        Product.ProductBuilder productBuilder = Product.builder()
                .productBrand(new ProductBrand(restProduct.getBrand()))
                .productColor(new ProductColor(restProduct.getColor()))
                .productDescription(new ProductDescription(restProduct.getDescription()))
                .productName(new ProductName(restProduct.getName()))
                .productPrice(new ProductPrice(restProduct.getPrice()))
                .productSize(restProduct.getSize())
                .category(RestCategory.toDomain(restProduct.getCategory()))
                .featured(restProduct.isFeatured())
                .pictures(RestPicture.toDomain(restProduct.getPictures()))
                .nbInStock(restProduct.getNbInStock());

        if (restProduct.getPublicId() != null) {
            productBuilder.publicId(new PublicId(restProduct.getPublicId()));
        }

        return productBuilder.build();
    }

    public static RestProduct fromDomain(Product product){
        return RestProduct.builder()
                .brand(product.getProductBrand().value())
                .color(product.getProductColor().value())
                .description(product.getProductDescription().value())
                .name(product.getProductName().value())
                .price(product.getProductPrice().value())
                .size(product.getProductSize())
                .category(RestCategory.fromDomain(product.getCategory()))
                .featured(product.isFeatured())
                .pictures(RestPicture.fromDomain(product.getPictures()))
                .publicId(product.getPublicId().value())
                .nbInStock(product.getNbInStock())
                .build();
    }

}
