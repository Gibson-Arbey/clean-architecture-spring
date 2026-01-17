package co.clean_architecture.usecase.user.command;

import java.util.Set;

public record CreateUserCommand(String username, String password, String email, Set<String> roleNames) {
}
