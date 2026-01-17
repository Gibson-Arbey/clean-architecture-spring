package co.clean_architecture.usecase.user;

import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.role.exception.InvalidRoleException;
import co.clean_architecture.model.role.gateways.RoleRepository;
import co.clean_architecture.model.user.Email;
import co.clean_architecture.model.user.Password;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.PasswordEncoderGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.model.user.exception.EmailAlreadyExistsException;
import co.clean_architecture.model.user.exception.UsernameAlreadyExistsException;
import co.clean_architecture.usecase.user.command.CreateUserCommand;
import co.clean_architecture.usecase.user.policy.PasswordPolicy;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderGateway passwordEncoderGateway;

    public User execute(CreateUserCommand command) {

        validateUniqueConstraints(command);

        Email email = new Email(command.email());

        PasswordPolicy.validate(command.password());

        String encodedPassword =
                passwordEncoderGateway.encode(command.password());

        Password password = new Password(encodedPassword);

        Set<Role> roles = roleRepository.findByNames(command.roleNames());

        if(roles.size() != command.roleNames().size()) {
            throw new InvalidRoleException("One or more roles are invalid");
        }

        User user = User.create(
                command.username(),
                password,
                email,
                roles
        );

        return userRepository.save(user);
    }

    private void validateUniqueConstraints(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(command.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
