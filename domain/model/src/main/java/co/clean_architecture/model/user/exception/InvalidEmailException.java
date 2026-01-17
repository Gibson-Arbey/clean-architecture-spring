package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_EMAIL";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
