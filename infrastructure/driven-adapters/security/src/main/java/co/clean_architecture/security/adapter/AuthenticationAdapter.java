package co.clean_architecture.security.adapter;

import co.clean_architecture.model.security.gateways.TokenGateway;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.gateways.AuthenticationGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationGateway {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenGateway tokenGateway;

    @Override
    public void authenticate(String username, String password) {
        // 1. Validar credenciales (Spring Security)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }
}
