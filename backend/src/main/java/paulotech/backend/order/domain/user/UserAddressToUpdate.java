package paulotech.backend.order.domain.user;

import lombok.Builder;
import paulotech.backend.shared.error.domain.Assert;

@Builder
public record UserAddressToUpdate(UserPublicId userPublic, UserAddress userAddress) {

    public UserAddressToUpdate {
        Assert.notNull("userPublic", userPublic);
        Assert.notNull("userAddress", userAddress);
    }
}
