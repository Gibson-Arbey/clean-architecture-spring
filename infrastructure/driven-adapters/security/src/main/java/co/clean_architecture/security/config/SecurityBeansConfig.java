package co.clean_architecture.security.config;

import co.clean_architecture.model.security.gateways.TokenGateway;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.security.filter.JwtAuthenticationFilter;
import co.clean_architecture.security.filter.UserStatusFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(TokenGateway tokenGateway) {
        return new JwtAuthenticationFilter(tokenGateway);
    }

    @Bean
    public UserStatusFilter userStatusFilter(UserRepository userRepository) {
        return new UserStatusFilter(userRepository);
    }

}