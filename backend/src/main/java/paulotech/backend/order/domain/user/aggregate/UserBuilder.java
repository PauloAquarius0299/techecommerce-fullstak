package paulotech.backend.order.domain.user.aggregate;

import paulotech.backend.order.domain.user.dto.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class UserBuilder {

    private UserLastname lastname;
    private UserFisrtname firstname;
    private UserEmail userEmail;
    private UserPublicId userPublicId;
    private UserImagemUrl userImagemUrl;
    private ImagemUrl imagemUrl;
    private Instant lastModifiedDate;
    private Instant createdDate;
    private Set<Authority> authorities;
    private Long dbId;
    private UserAddress userAddress;
    private Instant lastSeen;

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public UserBuilder lastname(UserLastname lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder firstname(UserFisrtname firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder userEmail(UserEmail userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public UserBuilder userPublicId(UserPublicId userPublicId) {
        this.userPublicId = userPublicId;
        return this;
    }

    public UserBuilder userImagemUrl(UserImagemUrl userImagemUrl) {
        this.userImagemUrl = userImagemUrl;
        return this;
    }

    public UserBuilder imagemUrl(ImagemUrl imagemUrl) {
        this.imagemUrl = imagemUrl;
        return this;
    }

    public UserBuilder lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public UserBuilder createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public UserBuilder authorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserBuilder dbId(Long dbId) {
        this.dbId = dbId;
        return this;
    }

    public UserBuilder userAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public UserBuilder lastSeen(Instant lastSeen) {
        this.lastSeen = lastSeen;
        return this;
    }

    public User build() {
        return new User(lastname, firstname, userEmail, userPublicId, userImagemUrl, imagemUrl,
                lastModifiedDate, createdDate, authorities, dbId, userAddress, lastSeen);
    }
}
