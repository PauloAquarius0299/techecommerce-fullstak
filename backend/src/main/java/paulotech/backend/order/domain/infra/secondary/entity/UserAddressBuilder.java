package paulotech.backend.order.domain.infra.secondary.entity;

import paulotech.backend.order.domain.user.dto.UserAddress;

public class UserAddressBuilder {
    private String street;
    private String city;
    private String zipCode;
    private String country;

    public static UserAddressBuilder userAddress() {
        return new UserAddressBuilder();
    }

    public UserAddressBuilder street(String street) {
        this.street = street;
        return this;
    }

    public UserAddressBuilder city(String city) {
        this.city = city;
        return this;
    }

    public UserAddressBuilder zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public UserAddressBuilder country(String country) {
        this.country = country;
        return this;
    }

    public UserAddress build() {
        return new UserAddress(street, city, zipCode, country);
    }
}

