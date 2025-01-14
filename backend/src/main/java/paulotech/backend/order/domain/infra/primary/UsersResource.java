package paulotech.backend.order.domain.infra.primary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paulotech.backend.order.application.UserApplicationService;
import paulotech.backend.order.domain.user.aggregate.User;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersResource {

    private final UserApplicationService userApplicationService;

    @GetMapping("/authenticated")
    public ResponseEntity<RestUser> getAuthenticatedUser(@AuthenticationPrincipal Jwt jwt,
                                                         @RequestParam boolean forceResync){
        User authenticatedUser = userApplicationService.getAuthenticatedUserWithSync(jwt, forceResync);
        RestUser restUser = RestUser.from(authenticatedUser);
        return ResponseEntity.ok(restUser);
    }
}
