package paulotech.backend.product.infra.secondary.entity;

import jakarta.persistence.*;
import jakarta.persistence.FetchType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import paulotech.backend.product.domain.aggregates.Product;
import paulotech.backend.product.domain.dto.*;

import paulotech.backend.shared.jpa.AbstractAuditingEntity;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name =  "product")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ProductEntity extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
    @SequenceGenerator(name = "productSequence", sequenceName = "product_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "featured")
    private Boolean featured;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private ProductSize size;

    @Column(name = "public_id", unique = true)
    private UUID publicId;

    @Column(name = "nb_in_stock")
    private int nbInStock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<PictureEntity> pictures = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_fk", referencedColumnName = "id")
    private CategoryEntity category;

    public ProductEntity(String brand, String color, String description, String name, double price, boolean featured, ProductSize size, UUID publicId, int nbInStock, CategoryEntity category) {
        this.brand = brand;
        this.color = color;
        this.description = description;
        this.name = name;
        this.price = price;
        this.featured = featured;
        this.size = size;
        this.publicId = publicId;
        this.nbInStock = nbInStock;
        this.category = category;
    }

    public static ProductEntity from(Product product){
        return ProductEntity.builder()
                .brand(product.getProductBrand().value())
                .color(product.getProductColor().value())
                .description(product.getProductDescription().value())
                .name(product.getProductName().value())
                .price(product.getProductPrice().value())
                .featured(product.isFeatured())
                .size(product.getProductSize())
                .publicId(product.getPublicId().value())
                .nbInStock(product.getNbInStock())
                .category(CategoryEntity.from(product.getCategory()))
                .build();

    }

    public static Product to(ProductEntity productEntity) {
        return Product.builder()
                .productBrand(new ProductBrand(productEntity.getBrand()))
                .productColor(new ProductColor(productEntity.getColor()))
                .productDescription(new ProductDescription(productEntity.getDescription()))
                .productName(new ProductName(productEntity.getName()))
                .productPrice(new ProductPrice(productEntity.getPrice()))
                .productSize(productEntity.getSize())
                .publicId(new PublicId(productEntity.getPublicId()))
                .category(CategoryEntity.to(productEntity.getCategory()))
//                .pictures(PictureEntity.to(productEntity.getPictures()))
                .featured(productEntity.getFeatured())
                .nbInStock(productEntity.getNbInStock())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity that)) return false;
        return featured == that.featured && Objects.equals(brand, that.brand) && Objects.equals(color, that.color) && Objects.equals(description, that.description) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && size == that.size && Objects.equals(publicId, that.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, color, description, name, price, featured, size, publicId);
    }

}


