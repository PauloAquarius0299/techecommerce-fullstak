package paulotech.backend.order.domain.user.dto;


import paulotech.backend.shared.error.domain.Assert;

public record UserFisrtname(String value) {

    public UserFisrtname {
        Assert.field("value", value).maxLength(255);
    }
}
