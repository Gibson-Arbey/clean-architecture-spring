package co.clean_architecture.jpa.mapper;

import co.clean_architecture.jpa.entity.RoleEntity;
import co.clean_architecture.jpa.entity.UserEntity;
import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.user.Email;
import co.clean_architecture.model.user.Password;
import co.clean_architecture.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserJpaMapper {

    private final RoleJpaMapper roleMapper;

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return User.restore(
            entity.getId(),
            entity.getUsername(),
            new Password(entity.getPassword()),
            new Email(entity.getEmail()),
            entity.getStatus(),
            toDomainRoles(entity.getRoles()),
            entity.getCreatedAt()
        );
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        return UserEntity
                .builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .password(domain.getPassword().value())
                .email(domain.getEmail().value())
                .status(domain.getStatus())
                .roles(toEntityRoles(domain.getRoles()))
                .createdAt(domain.getCreatedAt())
                .build();
    }

    private Set<Role> toDomainRoles(Set<RoleEntity> entities) {
        return entities == null
                ? Set.of()
                : entities.stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toSet());
    }

    private Set<RoleEntity> toEntityRoles(Set<Role> roles) {
        return roles == null
                ? Set.of()
                : roles.stream()
                .map(roleMapper::toEntity)
                .collect(Collectors.toSet());
    }

}
