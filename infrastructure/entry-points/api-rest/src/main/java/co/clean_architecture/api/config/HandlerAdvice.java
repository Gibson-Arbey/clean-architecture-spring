package co.clean_architecture.api.config;

import co.clean_architecture.model.error.ErrorResponse;
import co.clean_architecture.model.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class HandlerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(DomainException ex) {
        HttpStatus status = switch (ex.getErrorType()) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case VALIDATION -> HttpStatus.BAD_REQUEST;
        };
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.error(errorResponse.toString());
        return ResponseEntity.status(status).body(
                errorResponse
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error(ex.getMessage());
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> build(
            HttpStatus status,
            String code,
            String message
    ) {
        return ResponseEntity.status(status).body(
                ErrorResponse.builder()
                        .code(code)
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
