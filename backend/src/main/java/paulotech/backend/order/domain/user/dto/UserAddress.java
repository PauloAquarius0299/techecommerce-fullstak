package paulotech.backend.order.domain.user.dto;


import org.jilt.Builder;
import paulotech.backend.shared.error.domain.Assert;

@Builder
public record UserAddress(String street, String city, String zipCode, String country) {

    public UserAddress{
        paulotech.backend.shared.error.domain.Assert.field("street", street).notNull();
        Assert.field("city", city).notNull();
        Assert.field("zipCode", zipCode).notNull();
        Assert.field("country", country).notNull();
    }
}
