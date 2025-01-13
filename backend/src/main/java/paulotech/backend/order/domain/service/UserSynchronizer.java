package paulotech.backend.order.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import paulotech.backend.order.domain.infra.secondary.service.kindle.KindleService;
import paulotech.backend.order.domain.user.aggregate.User;
import paulotech.backend.order.domain.user.dto.UserAddress;
import paulotech.backend.order.domain.user.dto.UserAddressToUpdate;
import paulotech.backend.order.domain.user.repositories.UserRepository;
import paulotech.backend.shared.authentification.application.AuthenticationUser;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSynchronizer {

    private final UserRepository  userRepository;
    private final KindleService kindleService;

    private static final String UPDATE_AT_KEY = "last_signed_in";

    public void syncWithIdp(Jwt jwtToken, boolean forceResync){
        Map<String, Object> claims = jwtToken.getClaims();
        List<String> rolesFromToken = AuthenticationUser.extractRolesFromToken(jwtToken);
        Map<String, Object> userInfo = kindleService.getUserInfo(claims.get("sub").toString());
        User user = User.fromTokenAttributes(userInfo, rolesFromToken);
        Optional<User> existingUser = userRepository.getOneByEmail(user.getUserEmail());
        if(existingUser.isPresent()){
            if(claims.get(UPDATE_AT_KEY) != null){
                Instant lastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idModefieldDate = Instant.ofEpochSecond((Integer) claims.get(UPDATE_AT_KEY));

                if(lastModifiedDate.isAfter(lastModifiedDate) || forceResync){
                    updateUser(user, existingUser.get());
                }
            }
        }else {
            user.initFieldForSignup();
            userRepository.save(user);
        }
    }

    private void updateUser(User user, User existingUser){
        existingUser.updateFromUser(user);
        userRepository.save(existingUser);
    }

    public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
        userRepository.updateAddress(userAddressToUpdate.userPublicId(), userAddressToUpdate);
    }
}
