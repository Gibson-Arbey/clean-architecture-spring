package co.clean_architecture.security.jwt;

import co.clean_architecture.security.config.SecurityConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final SecurityConstants securityConstants;

    public String createToken(String username, Long userId, Set<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(securityConstants.getJwtKeyPrivate());
        return JWT.create()
                .withIssuer(securityConstants.getJwtUserGenerator())
                .withSubject(username)
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withClaim("roles", roles.stream().map(r -> "ROLE_" + r).toList())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 14400000))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(securityConstants.getJwtKeyPrivate());
        return JWT.require(algorithm)
                .withIssuer(securityConstants.getJwtUserGenerator())
                .build()
                .verify(token);
    }

    public Long extractUserId(DecodedJWT jwt) {
        return jwt.getClaim("userId").asLong();
    }

    public Set<String> extractRoles(DecodedJWT jwt) {
        return Set.copyOf(jwt.getClaim("roles").asList(String.class));
    }

    public String extractUsername(DecodedJWT jwt) {
        return jwt.getClaim("username").asString();
    }

}
