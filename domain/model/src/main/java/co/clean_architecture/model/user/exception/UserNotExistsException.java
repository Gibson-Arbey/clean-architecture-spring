package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class UserNotExistsException extends DomainException {
    public UserNotExistsException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "USER_NOT_EXISTS";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
