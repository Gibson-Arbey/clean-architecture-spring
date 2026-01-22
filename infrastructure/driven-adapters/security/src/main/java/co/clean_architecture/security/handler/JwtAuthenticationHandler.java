package co.clean_architecture.security.handler;

import co.clean_architecture.security.exception.JwtAuthenticationException;
import co.clean_architecture.security.exception.UserStatusException;
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
public class JwtAuthenticationHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException ex
    ) throws IOException {

        String code = "AUTHENTICATION_INVALID";

         if (ex instanceof UserStatusException userStatusEx) {
            code = userStatusEx.getCode();
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

