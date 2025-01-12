package paulotech.backend.order.domain.user;


import paulotech.backend.shared.error.domain.Assert;

public record UserLastname(String value) {

    public UserLastname{
        Assert.field("value", value).maxLength(255);
    }
}
