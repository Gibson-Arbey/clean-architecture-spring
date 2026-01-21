package co.clean_architecture.usecase.auth;

import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.security.AuthResult;
import co.clean_architecture.model.security.gateways.TokenGateway;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.AuthenticationGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthenticateUserUseCase {

    private final AuthenticationGateway authenticationGateway;
    private final TokenGateway tokenGateway;
    private final UserRepository userRepository;

    public AuthResult execute(String username, String password) {

        // 1. Autenticar credenciales
        authenticationGateway.authenticate(username, password);

        // 2. Obtener usuario del dominio
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generar token con roles
        String token = tokenGateway.generateToken(
                user.getId(),
                user.getUsername(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );

        return AuthResult.of(
                user.getId(),
                user.getUsername(),
                user.getEmail().value(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                token
        );
    }
}
