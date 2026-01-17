package co.clean_architecture.model.role.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class InvalidRoleException extends DomainException {
    public InvalidRoleException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_ROLE";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
