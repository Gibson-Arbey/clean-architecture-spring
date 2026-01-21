package co.clean_architecture.api.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private final String token;
    private final AuthUserResponse user;
}
