package paulotech.backend.order.domain.infra.secondary.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import paulotech.backend.order.domain.infra.secondary.entity.UserEntity;
import paulotech.backend.order.domain.user.aggregate.User;
import paulotech.backend.order.domain.user.dto.UserAddressToUpdate;
import paulotech.backend.order.domain.user.dto.UserEmail;
import paulotech.backend.order.domain.user.dto.UserPublicId;
import paulotech.backend.order.domain.user.repositories.UserRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataUserRepository implements UserRepository {

    private final UserJpaRepository repository;

    @Override
    public void save(User user) {
        if(user.getDbId() != null){
            Optional<UserEntity> userToUpadateOpt = repository.findById(user.getDbId());
            if(userToUpadateOpt.isPresent()){
                UserEntity userToUpdate = userToUpadateOpt.get();
                userToUpdate.updateFromUser(user);
                repository.save(userToUpdate);
            }
        }else{
            repository.save(UserEntity.from(user));
        }
    }

    @Override
    public Optional<User> get(UserPublicId userPublicId) {
        return repository.findOneByPublicId(userPublicId.value())
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> getOneByEmail(UserEmail userEmail) {
        return repository.findByEmail(userEmail.value())
                .map(UserEntity::toDomain);
    }

    @Override
    public void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddress) {
        repository.updateAddress(userPublicId.value(),
                userAddress.userAddress().street(),
                userAddress.userAddress().city(),
                userAddress.userAddress().country(),
                userAddress.userAddress().zipCode());
    }
}
