package paulotech.backend.order.domain.user.dto;

import paulotech.backend.shared.error.domain.Assert;

public record UserImagemUrl(String value) {

    public UserImagemUrl{
        Assert.field("value", value).maxLength(1000);
    }
}
