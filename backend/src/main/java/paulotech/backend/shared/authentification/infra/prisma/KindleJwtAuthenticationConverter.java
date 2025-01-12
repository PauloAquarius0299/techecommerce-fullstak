package paulotech.backend.shared.authentification.infra.prisma;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import paulotech.backend.shared.authentification.application.AuthenticationUser;


import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class KindleJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(source,
                Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source).stream(), extractResourceRoles(source).stream()).collect(Collectors.toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        return AuthenticationUser.extractRolesFromToken(jwt).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
