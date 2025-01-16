package paulotech.backend.product.domain.aggregates;

import paulotech.backend.shared.error.domain.Assert;

public record Picture(byte[] file, String mimeType) {

    public Picture{
        Assert.notNull("file", file);
        Assert.notNull("mimeType", mimeType);
    }
}
