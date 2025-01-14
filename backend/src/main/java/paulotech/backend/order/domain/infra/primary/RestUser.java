package paulotech.backend.order.domain.infra.primary;

import org.jilt.Builder;
import paulotech.backend.order.domain.user.aggregate.User;

import java.util.Set;
import java.util.UUID;


public record RestUser(UUID publicId,
                       String firstName,
                       String lastName,
                       String email,
                       String imageUrl,
                       Set<String> authorities) {

    public static RestUser from(User user){
        RestUserBuilder restUserBuilder = RestUserBuilder.restUser();

        if(user.getImagemUrl() != null){
            restUserBuilder.imageUrl(user.getImagemUrl().value());
        }

        return restUserBuilder
                .email(user.getUserEmail().value())
                .firstName(user.getFisrtname().value())
                .lastName(user.getLastname().value())
                .publicId(user.getUserPublicId().value())
                .authorities(RestAuthority.fromSet(user.getAuthorities()))
                .build();
    }

}
