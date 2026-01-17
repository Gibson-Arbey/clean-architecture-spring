package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.mapper.RoleJpaMapper;
import co.clean_architecture.jpa.repository.RoleJpaRepository;
import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.role.gateways.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleJpaMapper roleJpaMapper;

    @Override
    public List<Role> findAll() {
        return roleJpaMapper
                .toDomainList(
                        roleJpaRepository.findAll()
                );
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleJpaRepository
                .findByName(name)
                .map(roleJpaMapper::toDomain);
    }

    @Override
    public Set<Role> findByNames(Set<String> names) {
        return roleJpaMapper.toDomainSet(
                roleJpaRepository.findByNameIn(names)
        );
    }
}
