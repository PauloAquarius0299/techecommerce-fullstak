package paulotech.backend.order.domain.infra.secondary.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import paulotech.backend.order.domain.user.aggregate.User;
import paulotech.backend.order.domain.user.dto.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "ecommerce_user")
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "public_id")
    private UUID publicId;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_zid_code")
    private String addressZipCode;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "last_seen")
    private Instant lastSeen;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String lastName, String firstName, String email, String imageUrl, UUID publicId, String addressStreet, String addressCity, String addressZidCode, String addressCountry, Instant lastSeen, Set<AuthorityEntity> authorities) {
        this.id = id;
        this.lastname = lastName;
        this.firstname = firstName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressZipCode = addressZidCode;
        this.addressCountry = addressCountry;
        this.lastSeen = lastSeen;
        this.authorities = authorities;
    }

    public void updateFromUser(User user) {
        this.email = user.getUserEmail().value();
        this.lastname = user.getLastname().value();
        this.firstname = user.getFisrtname().value();
        this.imageUrl = user.getImagemUrl().value();
        this.lastSeen = user.getLastSeen();
    }

    public static UserEntity from(User user){
        UserEntityBuilder userEntityBuilder = UserEntity.builder();

        if(user.getImagemUrl() != null){
            userEntityBuilder.imageUrl(user.getImagemUrl().value());
        }

        if (user.getUserPublicId() != null) {
            userEntityBuilder.publicId(user.getUserPublicId().value());
        }

        if(user.getUserAddress() != null){
            userEntityBuilder.addressStreet(user.getUserAddress().street());
            userEntityBuilder.addressCity(user.getUserAddress().city());
            userEntityBuilder.addressZipCode(user.getUserAddress().zipCode());
            userEntityBuilder.addressCountry(user.getUserAddress().country());
        }

        return userEntityBuilder
                .authorities(AuthorityEntity.from(user.getAuthorities()))
                .firstname(user.getFisrtname().value())
                .lastname(user.getLastname().value())
                .email(user.getUserEmail().value())
                .lastSeen(user.getLastSeen())
                .id(user.getDbId())
                .build();
    }

    public static User toDomain(UserEntity userEntity){
        User.UserBuilder userBuilder = User.builder();

        if(userEntity.getImageUrl() != null){
            userBuilder.userImagemUrl(new UserImagemUrl(userEntity.getImageUrl()));
        }

        if(userEntity.getAddressStreet() != null){
            userBuilder.userAddress(
                    new UserAddress(
                            userEntity.getAddressStreet(),
                            userEntity.getAddressCity(),
                            userEntity.getAddressZipCode(),
                            userEntity.getAddressCountry()
                    )
            );
        }

        return userBuilder
                .userEmail(new UserEmail(userEntity.getEmail()))
                .lastname(new UserLastname(userEntity.getLastname()))
                .fisrtname(new UserFisrtname(userEntity.getFirstname()))
                .authorities(AuthorityEntity.toDomain(userEntity.getAuthorities()))
                .userPublicId(new UserPublicId(userEntity.getPublicId()))
                .dbId(userEntity.getId())
                .build();
    }

    public static Set<UserEntity> from(List<User> users) {
        return users.stream().map(UserEntity::from).collect(Collectors.toSet());
    }

    public static Set<User> toDomain(List<UserEntity> users) {
        return users.stream().map(UserEntity::toDomain).collect(Collectors.toSet());
    }


}
