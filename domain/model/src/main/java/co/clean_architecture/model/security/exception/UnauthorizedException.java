package co.clean_architecture.model.security.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class UnauthorizedException extends DomainException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "UNAUTHORIZED_ERROR";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.UNAUTHORIZED;
    }
}
