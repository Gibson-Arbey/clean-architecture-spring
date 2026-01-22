package co.clean_architecture.security.exception;


import org.springframework.security.core.AuthenticationException;

public class UserStatusException extends AuthenticationException {

  private final String code;

  public UserStatusException(String msg) {
    super(msg);
    this.code = "USER_INACTIVE";
  }

  public String getCode() {
    return code;
  }
}
