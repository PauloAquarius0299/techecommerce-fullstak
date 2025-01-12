package paulotech.backend.order.domain.user.repositories;

import paulotech.backend.order.domain.user.aggregate.User;
import paulotech.backend.order.domain.user.dto.UserAddress;
import paulotech.backend.order.domain.user.dto.UserEmail;
import paulotech.backend.order.domain.user.dto.UserPublicId;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> get(UserPublicId userPublicId);

    Optional<User> get(UserEmail userEmail);

    void updateAddress(UserPublicId userPublicId, UserAddress userAddress);
}
