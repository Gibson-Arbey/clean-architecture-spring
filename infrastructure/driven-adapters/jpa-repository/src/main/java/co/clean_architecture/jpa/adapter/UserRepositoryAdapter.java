package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.mapper.UserJpaMapper;
import co.clean_architecture.jpa.repository.UserJpaRepository;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.criteria.UserCriteria;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    @Transactional
    public User save(User user) {
        return userJpaMapper.toDomain(
                userJpaRepository.save(
                        userJpaMapper.toEntity(user)
                )
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userJpaMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userJpaMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(userJpaMapper::toDomain);
    }

    @Override
    public List<User> findAllByFilters(UserCriteria criteria) {
        return userJpaRepository.findALlByFilters(
                criteria.getUsername(),
                criteria.getEmail(),
                criteria.isApplyStatusFilter(),
                criteria.getStatus(),
                criteria.isApplyRolesFilter(),
                criteria.getRoles(),
                criteria.getLimit(),
                criteria.getOffset()
        ).stream()
                .map(userJpaMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void updateStatus(Long id, String status) {
        userJpaRepository.updateStatus(id, status);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsernameAndIdNot(String username, Long id) {
        return userJpaRepository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return userJpaRepository.existsByEmailAndIdNot(email, id);
    }
}
