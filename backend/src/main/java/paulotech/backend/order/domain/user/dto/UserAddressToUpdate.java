package paulotech.backend.order.domain.user.dto;


import org.jilt.Builder;
import paulotech.backend.shared.error.domain.Assert;

@Builder
public record UserAddressToUpdate(UserPublicId userPublicId, UserAddress userAddress) {

    public UserAddressToUpdate {
        Assert.notNull("userPublicId", userPublicId);
        Assert.notNull("userAddress", userAddress);
    }
}
