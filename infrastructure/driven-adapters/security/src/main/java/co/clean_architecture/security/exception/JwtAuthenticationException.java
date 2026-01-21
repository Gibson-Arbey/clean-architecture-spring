package co.clean_architecture.security.exception;

import org.springframework.security.core.AuthenticationException;
import co.clean_architecture.model.exception.DomainException;

public class JwtAuthenticationException extends AuthenticationException {

    private final String code;

    public JwtAuthenticationException(DomainException ex) {
        super(ex.getMessage());
        this.code = ex.getCode();
    }

    public String getCode() {
        return code;
    }
}
