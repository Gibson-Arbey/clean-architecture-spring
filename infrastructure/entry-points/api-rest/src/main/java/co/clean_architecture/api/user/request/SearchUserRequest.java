package co.clean_architecture.api.user.request;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class SearchUserRequest {

    private String username;
    private String email;
    private List<String> status;
    private Set<String> roles;
    private Integer limit;
    private Integer offset;
}
