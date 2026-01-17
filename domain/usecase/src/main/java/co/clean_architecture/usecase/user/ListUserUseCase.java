package co.clean_architecture.usecase.user;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.criteria.UserCriteria;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListUserUseCase {

    private final UserRepository userRepository;

    public List<User> getUsersByCriteria(UserCriteria criteria) {
        return userRepository.findAllByFilters(criteria);
    }
}
