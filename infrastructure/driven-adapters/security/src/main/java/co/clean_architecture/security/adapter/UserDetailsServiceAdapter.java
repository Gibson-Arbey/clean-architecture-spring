package co.clean_architecture.security.adapter;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.UserStatus;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.exception.UserStatusNotValidException;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotExistsException("User not found"));

        if(!user.getStatus().equals(UserStatus.ACTIVE.name())) {
            throw new UserStatusNotValidException("User is not active");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword().value())
                .authorities(
                        user.getRoles()
                                .stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                                .collect(Collectors.toSet())
                )
                .accountLocked(false)
                .disabled(false)
                .build();
    }
}
