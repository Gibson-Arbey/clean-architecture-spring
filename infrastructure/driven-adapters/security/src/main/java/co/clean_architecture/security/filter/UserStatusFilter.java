package co.clean_architecture.security.filter;

import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.security.exception.UserStatusException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class UserStatusFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            Long userId = (Long) authentication.getDetails();

            if (!userRepository.userStatusIsActive(userId)) {
                throw new UserStatusException(
                        "User account is not active."
                );
            }
        }

        filterChain.doFilter(request, response);
    }
}
