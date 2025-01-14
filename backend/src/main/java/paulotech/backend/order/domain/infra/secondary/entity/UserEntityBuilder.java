package paulotech.backend.order.domain.infra.secondary.entity;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class UserEntityBuilder {
    private String imageUrl;
    private UUID publicId;
    private String addressStreet;
    private String addressCity;
    private String addressZipCode;
    private String addressCountry;
    private Set<AuthorityEntity> authorities;
    private String firstname;
    private String lastname;
    private String email;
    private Long id;
    private Instant lastSeen;

    public static UserEntityBuilder userEntity() {
        return new UserEntityBuilder();
    }

    public UserEntityBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserEntityBuilder publicId(UUID publicId) {
        this.publicId = publicId;
        return this;
    }

    public UserEntityBuilder addressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
        return this;
    }

    public UserEntityBuilder addressCity(String addressCity) {
        this.addressCity = addressCity;
        return this;
    }

    public UserEntityBuilder addressZidCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
        return this;
    }

    public UserEntityBuilder addressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
        return this;
    }

    public UserEntityBuilder authorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserEntityBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserEntityBuilder lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserEntityBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserEntityBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserEntityBuilder lastSeen(Instant lastSeen) {
        this.lastSeen = lastSeen;
        return this;
    }

    public UserEntity build() {
        UserEntity userEntity = new UserEntity();
        userEntity.setImageUrl(this.imageUrl);
        userEntity.setPublicId(this.publicId);
        userEntity.setAddressStreet(this.addressStreet);
        userEntity.setAddressCity(this.addressCity);
        userEntity.setAddressZidCode(this.addressZipCode);
        userEntity.setAddressCountry(this.addressCountry);
        userEntity.setAuthorities(this.authorities);
        userEntity.setFirstname(this.firstname);
        userEntity.setLastname(this.lastname);
        userEntity.setEmail(this.email);
        userEntity.setId(this.id);
        userEntity.setLastSeen(this.lastSeen);
        return userEntity;
    }
}
