package co.clean_architecture.api.user.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdateUserRequest {

    private String username;
    private String password;
    private String email;
    private Set<String> roleNames;
}
