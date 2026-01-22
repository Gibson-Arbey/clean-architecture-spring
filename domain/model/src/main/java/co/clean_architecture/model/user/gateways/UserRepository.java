package co.clean_architecture.model.user.gateways;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.criteria.UserCriteria;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAllByFilters(UserCriteria criteria);

    void updateStatus(Long id, String status);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean userStatusIsActive(Long id);

}
