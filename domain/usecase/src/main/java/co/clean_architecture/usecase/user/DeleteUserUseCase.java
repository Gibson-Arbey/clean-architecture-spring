package co.clean_architecture.usecase.user;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.UserStatus;
import co.clean_architecture.model.user.exception.UserAlreadyDeletedException;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public void deleteById(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotExistsException("User with ID " + userId + " does not exist."));

        if (UserStatus.DELETED.name().equals(user.getStatus())) {
            throw new UserAlreadyDeletedException("User with ID " + userId + " is already deleted.");
        }

        userRepository.updateStatus(userId, UserStatus.DELETED.name());
    }

}
