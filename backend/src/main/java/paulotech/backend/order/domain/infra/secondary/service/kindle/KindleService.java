package paulotech.backend.order.domain.infra.secondary.service.kindle;

import org.apache.hc.core5.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;


@Service
public class KindleService {

    private static final Logger log = LoggerFactory.getLogger(KindleService.class);
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

    private Optional<String> getToken(){
        try{
            ResponseEntity<KindleAccessToken> accessToken = restClient.post()
                    .uri(kindleApi + "/oauth/token")
                    .body("grant_type=client_credentials&audience=" + URLEncoder.encode(audience, StandardCharsets.UTF_8))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .header("Authorization",
                            "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8)))
                .header("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                    .retrieve()
                    .toEntity(KindleAccessToken.class);
            return Optional.of(accessToken.getBody().accessToken());
        } catch (Exception e){
            log.error("Erro ao obter token: {}", e.getMessage(), e);
            throw new IllegalStateException("Erro ao obter token", e);

        }
    }

    public Map<String,Object> getUserInfo( String userId ){
        String token = getToken().orElseThrow(() -> new IllegalStateException("Nenhum token encontrado"));

        var typeRef = new ParameterizedTypeReference<Map<String, Object>>() {};

        ResponseEntity<Map<String, Object>> authorization = restClient.get()
                .uri(String.format("%s/api/v1/user?id=%s", kindleApi, userId))
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .toEntity(typeRef);

        return authorization.getBody();
    }
}
