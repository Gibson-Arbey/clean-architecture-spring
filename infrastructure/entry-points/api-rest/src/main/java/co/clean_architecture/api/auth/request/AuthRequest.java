package co.clean_architecture.api.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;
}
