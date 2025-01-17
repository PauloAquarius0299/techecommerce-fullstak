package paulotech.backend.product.domain.dto;


import paulotech.backend.shared.error.domain.Assert;

public record ProductBrand(String value) {

    public ProductBrand {
        Assert.field("value", value).notNull().minLength(3);
    }

}
