package co.clean_architecture.model.user;

import co.clean_architecture.model.role.Role;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class User {

    private final Long id;
    private final String username;
    private final Password password;
    private final Email email;
    private final String status;
    private final Set<Role> roles;
    private final LocalDateTime createdAt;

    private User(Long id, String username, Password password, Email email, String status, Set<Role> roles, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.roles = roles;
        this.createdAt = createdAt;
    }

    public static User create(String username, Password password, Email email,  Set<Role> roles) {
        return new User(
                null,
                username,
                password,
                email,
                UserStatus.ACTIVE.name(),
                roles,
                LocalDateTime.now()
        );

    }

    public static User restore(Long id, String username, Password password, Email email, String status, Set<Role> roles, LocalDateTime createdAt) {
        return new User(
                id,
                username,
                password,
                email,
                status,
                roles,
                createdAt
        );
    }

    public User withBasicInfo(String username, Email email) {
        return new User(
                this.id,
                username != null ? username : this.username,
                this.password,
                email != null ? email : this.email,
                this.status,
                this.roles,
                this.createdAt
        );
    }

    public User withPassword(Password password) {
        return new User(
                this.id,
                this.username,
                password,
                this.email,
                this.status,
                this.roles,
                this.createdAt
        );
    }

    public User withRoles(Set<Role> roles) {
        return new User(
                this.id,
                this.username,
                this.password,
                this.email,
                this.status,
                roles,
                this.createdAt
        );
    }


}
