package co.clean_architecture.api.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String status;
    private Set<String> roles;
}
