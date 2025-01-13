package paulotech.backend.order.domain.user.aggregate;

import paulotech.backend.order.domain.infra.secondary.entity.AuthorityEntity;

public class AuthorityEntityBuilder {
    private String name;

    public static AuthorityEntityBuilder authorityEntity() {
        return new AuthorityEntityBuilder();
    }

    public AuthorityEntityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AuthorityEntity build() {
        return new AuthorityEntity(this.name);
    }
}
