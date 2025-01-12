package paulotech.backend.order.domain.user.aggregate;

import lombok.Getter;
import lombok.Setter;
import org.jilt.Builder;
import paulotech.backend.order.domain.user.dto.*;
import paulotech.backend.shared.error.domain.Assert;


import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
public class User {

    private UserLastname lastname;

    private UserFisrtname fisrtname;

    private UserEmail userEmail;

    private UserPublicId userPublicId;

    private UserImagemUrl userImagemUrl;

    private ImagemUrl imagemUrl;

    private Instant lastModifiedDate;

    private Instant  createdDate;

    private Set<Authority> authorities;

    private Long dbId;

    private UserAddress userAddress;

    private Instant lastSeen;

    public User(UserLastname lastname, UserFisrtname fisrtname, UserEmail userEmail, UserPublicId userPublicId,
                UserImagemUrl userImagemUrl, ImagemUrl imagemUrl, Instant lastModifiedDate, Instant createdDate,
                Set<Authority> authorities, Long dbId, UserAddress userAddress, Instant lastSeen) {
        this.lastname = lastname;
        this.fisrtname = fisrtname;
        this.userEmail = userEmail;
        this.userPublicId = userPublicId;
        this.userImagemUrl = userImagemUrl;
        this.imagemUrl = imagemUrl;
        this.lastModifiedDate = lastModifiedDate;
        this.createdDate = createdDate;
        this.authorities = authorities;
        this.dbId = dbId;
        this.userAddress = userAddress;
        this.lastSeen = lastSeen;
    }

    private void assertMandatoryFields() {
        Assert.notNull("lastname", lastname);
        Assert.notNull("fisrtname", fisrtname);
        Assert.notNull("userEmail", userEmail);
        Assert.notNull("authorities", authorities);
    }

    public void updateFromUser(User user){
        this.userEmail = user.userEmail;
        this.imagemUrl = user.imagemUrl;
        this.lastname = user.lastname;
        this.fisrtname = user.fisrtname;
    }

    public void initFieldForSignup() {
        this.userPublicId = new UserPublicId(UUID.randomUUID());
    }


}
