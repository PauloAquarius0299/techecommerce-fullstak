package paulotech.backend.order.domain.infra.secondary.service.kindle;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KindleAccessToken(@JsonProperty("access_token") String accessToken,
                                @JsonProperty("token_type") String tokenType) {
}
