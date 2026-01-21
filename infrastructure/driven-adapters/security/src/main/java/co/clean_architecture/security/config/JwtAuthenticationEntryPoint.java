package co.clean_architecture.security.config;

import co.clean_architecture.security.exception.JwtAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException ex
    ) throws IOException {

        String code = "AUTH_001";

        if (ex instanceof JwtAuthenticationException jwtEx) {
            code = jwtEx.getCode();
        }

        co.clean_architecture.model.error.ErrorResponse error = co.clean_architecture.model.error.ErrorResponse.builder()
                .code(code)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}

