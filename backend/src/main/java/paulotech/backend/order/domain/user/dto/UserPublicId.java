package paulotech.backend.order.domain.user.dto;

import paulotech.backend.shared.error.domain.Assert;

import java.util.UUID;

public record UserPublicId(UUID value) {

    public UserPublicId {
        Assert.notNull("value", value);
    }
}
