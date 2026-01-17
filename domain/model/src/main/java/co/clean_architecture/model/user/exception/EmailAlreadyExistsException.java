package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "EMAIL_ALREADY_EXISTS";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
