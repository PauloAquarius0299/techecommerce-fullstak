package paulotech.backend.order.domain.infra.primary;

import org.jilt.Builder;
import paulotech.backend.order.domain.user.aggregate.Authority;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record RestAuthority(String name) {

    public static Set<String> fromSet(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> authority.getName().name()) // Certifique-se de que `getName().name()` retorna uma String
                .collect(Collectors.toSet());
    }
}
