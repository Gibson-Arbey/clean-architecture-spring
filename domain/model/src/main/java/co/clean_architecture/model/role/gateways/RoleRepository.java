package co.clean_architecture.model.role.gateways;

import co.clean_architecture.model.role.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository {

    List<Role> findAll();

    Optional<Role> findByName(String name);

    Set<Role> findByNames(Set<String> names);
}
