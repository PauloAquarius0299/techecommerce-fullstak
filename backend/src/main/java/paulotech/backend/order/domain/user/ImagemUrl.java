package paulotech.backend.order.domain.user;

import paulotech.backend.shared.error.domain.Assert;

public record ImagemUrl(String value) {

    public ImagemUrl{
        Assert.field("value", value).maxLength(1000);
    }
}
