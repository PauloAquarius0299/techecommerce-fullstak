package paulotech.backend.order.domain.user;

import paulotech.backend.shared.error.domain.Assert;

public record AuthorityName(String name) {

    public AuthorityName {
        Assert.field("name", name).notNull();
    }

}
