package paulotech.backend.order.domain.user.aggregate;

import paulotech.backend.order.domain.user.dto.AuthorityName;

public class AuthorityBuilder {

    private AuthorityName name;

    public static AuthorityBuilder authority() {
        return new AuthorityBuilder();
    }

    public AuthorityBuilder name(AuthorityName name) {
        this.name = name;
        return this;
    }

    public Authority build() {
        return new Authority(name);
    }
}
