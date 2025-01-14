package paulotech.backend.order.domain.infra.primary;

import java.util.Set;
import java.util.UUID;

public class RestUserBuilder {
    private String imageUrl;
    private UUID publicId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> authorities;

    public RestUserBuilder publicId(UUID publicId) {
        this.publicId = publicId;
        return this;
    }

    public static RestUserBuilder restUser() {
        return new RestUserBuilder();
    }

    public RestUserBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public RestUserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RestUserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RestUserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public RestUserBuilder authorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }

    public RestUser build() {
        return new RestUser(publicId, firstName, lastName, email, imageUrl, authorities);
    }
}

