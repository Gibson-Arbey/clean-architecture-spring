package co.clean_architecture.model.security.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class ForbiddenException extends DomainException {
    public ForbiddenException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "FORBIDDEN_ERROR";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.UNAUTHORIZED;
    }
}
