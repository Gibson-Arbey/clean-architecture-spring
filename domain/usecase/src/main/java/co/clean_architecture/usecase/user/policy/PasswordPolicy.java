package co.clean_architecture.usecase.user.policy;

import co.clean_architecture.model.user.exception.InvalidPasswordException;

public class PasswordPolicy {

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#._-]).{8,}$";

    private PasswordPolicy() {}

    public static void validate(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new InvalidPasswordException("Password must not be empty");
        }
        if (!rawPassword.matches(PASSWORD_REGEX)) {
            throw new InvalidPasswordException("Password does not meet security requirements");
        }
    }
}
