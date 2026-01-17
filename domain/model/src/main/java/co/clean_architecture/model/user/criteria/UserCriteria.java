package co.clean_architecture.model.user.criteria;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class UserCriteria {

    private String username;
    private String email;
    private boolean applyStatusFilter;
    private List<String> status;
    private boolean applyRolesFilter;
    private Set<String> roles;
    private int limit;
    private int offset;

    private UserCriteria(
            String username,
            String email,
            List<String> status,
            Set<String> roles,
            int limit,
            int offset
    ) {
        this.username = username != null ? username.trim() : null;
        this.email = email != null ? email.trim() : null;
        this.applyStatusFilter = status != null && !status.isEmpty();
        this.status = applyStatusFilter ? status : List.of();
        this.applyRolesFilter = roles != null && !roles.isEmpty();
        this.roles = applyRolesFilter ? roles : Set.of();
        this.limit = limit;
        this.offset = offset;
    }
    public static UserCriteria of(String username,
                                  String email,
                                  List<String> status,
                                  Set<String> roles,
                                  Integer limit,
                                  Integer offset) {
        return new UserCriteria(
                username,
                email,
                status,
                roles,
                limit != null ? limit : 20,
                offset != null ? offset : 0
        );
    }
}
