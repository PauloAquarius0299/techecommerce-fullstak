package paulotech.backend.order.domain.infra.secondary.service.kindle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class KindleService {

    @Value("${application.kindle.api}")
    private String kindleApi;

    @Value("${application.kindle.client-id}")
    private String clientId;

    @Value("${application.kindle.client-secret}")
    private String clientSecret;

    @Value("${application.kindle.audience}")
    private String audience;

    private final RestClient restClient = RestClient.builder()
            .requestFactory(new HttpComponentsClientHttpRequestFactory())
            .baseUrl(kindleApi)
            .build();
}
