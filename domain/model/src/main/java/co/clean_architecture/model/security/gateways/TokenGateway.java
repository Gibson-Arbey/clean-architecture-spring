package co.clean_architecture.model.security.gateways;

import java.util.Set;

public interface TokenGateway {

    String generateToken(Long userId, String username, Set<String> roles);

    Long extractUserId(String token);

    String extractUsername(String token);

    Set<String> extractRoles(String token);
}
