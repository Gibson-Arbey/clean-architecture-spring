package co.clean_architecture.jpa.mapper;

import co.clean_architecture.jpa.entity.RoleEntity;
import co.clean_architecture.model.role.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RoleJpaMapper {

    public Role toDomain(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        return Role.restore(
            entity.getId(),
            entity.getName()
        );
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        }
        return RoleEntity
                .builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public List<Role> toDomainList(List<RoleEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    public  List<RoleEntity> toEntityList(List<Role> roles) {
        return roles.stream()
                .map(this::toEntity)
                .toList();
    }

    public Set<Role> toDomainSet(Set<RoleEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(java.util.stream.Collectors.toSet());
    }
}
