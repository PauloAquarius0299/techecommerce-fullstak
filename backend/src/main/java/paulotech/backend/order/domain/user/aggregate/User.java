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

    public static User fromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken){
        UserBuilder  userBuilder = UserBuilder.user();

        if(attributes.containsKey("preferred_email")){
            userBuilder.userEmail(new UserEmail(attributes.get("preferred_email").toString()));
        }

        if(attributes.containsKey("last_name")){
            userBuilder.lastname(new UserLastname(attributes.get("last_name").toString()));
        }

        if(attributes.containsKey("first_name")){
            userBuilder.firstname(new UserFisrtname(attributes.get("first_name").toString()));
        }

        if(attributes.containsKey("picture")){
            userBuilder.userImagemUrl(new UserImagemUrl(attributes.get("picture").toString()));
        }

        if(attributes.containsKey("last_signed_in")){
            userBuilder.lastSeen(Instant.parse(attributes.get("last_signed_in").toString()));
        }

        Set<Authority> authorities = rolesFromAccessToken
                .stream()
                .map(authority -> new AuthorityBuilder().authority().name(new AuthorityName(authority)).build())
                .collect(Collectors.toSet());

        userBuilder.authorities(authorities);

        return userBuilder.build();
    }


}
