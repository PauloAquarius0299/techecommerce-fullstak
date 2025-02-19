package paulotech.backend.order.domain.user.dto;

import paulotech.backend.shared.error.domain.Assert;

public record UserEmail(String value) {
    public UserEmail{
        Assert.field("value", value).maxLength(255);
    }
}
