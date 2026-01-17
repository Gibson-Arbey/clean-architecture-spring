package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class UsernameAlreadyExistsException extends DomainException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "USERNAME_ALREADY_EXISTS";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
