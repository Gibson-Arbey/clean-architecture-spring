package co.clean_architecture.model.user.exception;

import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.exception.ErrorTypeEnum;

public class UserAlreadyDeletedException extends DomainException {
    public UserAlreadyDeletedException(String message) {
        super(message);
    }

  @Override
  public String getCode() {
    return "USER_ALREADY_DELETED";
  }

  @Override
  public ErrorTypeEnum getErrorType() {
    return ErrorTypeEnum.VALIDATION;
  }
}
