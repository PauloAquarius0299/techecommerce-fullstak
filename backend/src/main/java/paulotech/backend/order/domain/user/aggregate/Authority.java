package paulotech.backend.order.domain.user.aggregate;

import lombok.Builder;
import paulotech.backend.order.domain.user.dto.AuthorityName;
import paulotech.backend.shared.error.domain.Assert;

@Builder
public class Authority {

    private AuthorityName name;

    public Authority(AuthorityName authorityName) {
        Assert.notNull("name", authorityName);
        this.name = authorityName;
    }

    public AuthorityName getName() {
        return name;
    }
}
