package co.clean_architecture.api.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class AuthUserResponse {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
}
