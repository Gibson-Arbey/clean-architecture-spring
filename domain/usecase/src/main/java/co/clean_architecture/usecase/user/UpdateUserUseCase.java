package co.clean_architecture.usecase.user;

import co.clean_architecture.model.role.gateways.RoleRepository;
import co.clean_architecture.model.user.Email;
import co.clean_architecture.model.user.Password;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.exception.EmailAlreadyExistsException;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.exception.UsernameAlreadyExistsException;
import co.clean_architecture.model.user.gateways.PasswordEncoderGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.usecase.user.command.CreateUserCommand;
import co.clean_architecture.usecase.user.command.UpdateUserCommand;
import co.clean_architecture.usecase.user.policy.PasswordPolicy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderGateway passwordEncoderGateway;

    public User execute(Long id, UpdateUserCommand command) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistsException("User does not exist"));

        validateUniqueConstraints(id, command);

        User updatedUser = user
                .withBasicInfo(command.username(), new Email(command.email()));

        if (command.password() != null && !command.password().isBlank()) {
            PasswordPolicy.validate(command.password());
            updatedUser = updatedUser.withPassword(
                    new Password(
                            passwordEncoderGateway.encode(command.password())
                    )
            );
        }

        if (command.roleNames() != null && !command.roleNames().isEmpty()) {
            updatedUser = updatedUser.withRoles(
                    roleRepository.findByNames(command.roleNames())
            );
        }

        return userRepository.save(updatedUser);
    }


    private void validateUniqueConstraints(Long id, UpdateUserCommand command) {

        if (command.username() != null &&
                userRepository.existsByUsernameAndIdNot(command.username(), id)) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (command.email() != null &&
                userRepository.existsByEmailAndIdNot(command.email(), id)) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
