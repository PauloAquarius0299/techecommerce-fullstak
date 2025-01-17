package paulotech.backend.product.domain.aggregates;

import lombok.Builder;
import paulotech.backend.shared.error.domain.Assert;

@Builder
public record Picture(byte[] file, String mimeType) {

    public Picture{
        Assert.notNull("file", file);
        Assert.notNull("mimeType", mimeType);
    }
}
