package paulotech.backend.order.domain.user.service;

import lombok.RequiredArgsConstructor;
import paulotech.backend.order.domain.user.aggregate.User;
import paulotech.backend.order.domain.user.dto.UserEmail;
import paulotech.backend.order.domain.user.dto.UserPublicId;
import paulotech.backend.order.domain.user.repositories.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public Optional<User> getByEmail(UserEmail userEmail){
        return userRepository.getOneByEmail(userEmail);
    }

    public Optional<User> getByPublicId(UserPublicId userPublicId){
        return userRepository.get(userPublicId);
    }
}
