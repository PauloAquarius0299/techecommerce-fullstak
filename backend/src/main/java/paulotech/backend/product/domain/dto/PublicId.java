package paulotech.backend.product.domain.dto;

import paulotech.backend.shared.error.domain.Assert;

import java.util.UUID;

public record PublicId(UUID value) {

    public PublicId {
        Assert.notNull("value", value);
    }
}
