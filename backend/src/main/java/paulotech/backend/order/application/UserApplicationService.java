package paulotech.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paulotech.backend.order.domain.user.aggregate.User;
import paulotech.backend.order.domain.user.dto.UserAddressToUpdate;
import paulotech.backend.order.domain.user.dto.UserEmail;
import paulotech.backend.order.domain.user.service.UserReader;
import paulotech.backend.order.domain.user.service.UserSynchronizer;
import paulotech.backend.shared.authentification.application.AuthenticationUser;


@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserSynchronizer userSynchronizer;
    private final UserReader userReader;

    @Transactional
    public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceResync){
        userSynchronizer.syncWithIdp(jwtToken, forceResync);
        return userReader.getByEmail(new UserEmail(AuthenticationUser.username().get()))
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getAuthenticatedUser(){
        return userReader.getByEmail(new UserEmail(AuthenticationUser.username().get()))
                .orElseThrow();
    }

    @Transactional
    public void updateAddress(UserAddressToUpdate userAddressToUpdate){
        userSynchronizer.updateAddress(userAddressToUpdate);
    }

}
