package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class UserStatusNotValidException extends DomainException {
    public UserStatusNotValidException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "USER_STATUS_NOT_VALID";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
