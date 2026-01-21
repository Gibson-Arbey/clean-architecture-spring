package co.clean_architecture.model.security;

import lombok.Getter;

import java.util.Set;

@Getter
public class AuthResult {

    private final Long userId;
    private final String username;
    private final String email;
    private final Set<String> roles;
    private final String token;

    private AuthResult(Long userId, String username, String email, Set<String> roles, String token) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    public static AuthResult of(Long userId, String username, String email, Set<String> roles, String token) {
        return new AuthResult(userId, username, email, roles, token);
    }

    //
}
